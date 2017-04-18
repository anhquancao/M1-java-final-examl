package server;

import models.Comment;
import models.Message;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by caoquan on 4/18/17.
 */
public class ComputeBestMessageThread extends Thread {

    private String filename;
    private List<Message> messages;
    private List<Comment> comments;
    private BufferedReader reader;
    private List<Message> bestMessages;

    public ComputeBestMessageThread(String filename) {
        this.filename = filename;
        this.messages = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.bestMessages = new ArrayList<>();
    }

    public List<Message> getBestMessages() {
        return bestMessages;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = new FileInputStream(this.filename);
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] splittedData = line.split("\\|");

                if (line.charAt(line.length() - 2) == '|') {
                    int idMessage = Integer.parseInt(splittedData[0]);
                    int idUser = Integer.parseInt(splittedData[1]);
                    String messageContent = splittedData[2];
                    String user = splittedData[3];
                    Message message = new Message(idMessage, idUser, messageContent, user);
                    messages.add(message);
//                    System.out.println(message);
                } else {
                    int idComment = Integer.parseInt(splittedData[0]);
                    int idUser = Integer.parseInt(splittedData[1]);
                    String commentContent = splittedData[2];
                    String user = splittedData[3];
                    Comment comment = null;
                    if (line.charAt(line.length() - 1) == '|') {
                        int pidComment = Integer.parseInt(splittedData[4]);
                        comment = new Comment(idComment, idUser, commentContent, user, pidComment);
                    } else {
                        int pidMessage = Integer.parseInt(splittedData[5]);
                        comment = new Comment(idComment, idUser, commentContent, pidMessage, user);

                    }
                    comments.add(comment);

                }
                this.messages.stream().forEach(m -> m.setValue(m.getScore()));

                // filter active message
                this.messages = this.messages.stream().filter(m -> m.getScore() > 0).collect(Collectors.toList());
                this.comments = this.comments.stream().filter(c -> c.getScore() > 0).collect(Collectors.toList());
                this.messages.stream().forEach(m -> {
                    List<Comment> associatedComments =
                            this.comments.stream().filter(c -> c.getPidMessage() == m.getIdMessage()).collect(Collectors.toList());

                    // Get all the score of associated comments including:
                    // - Comments associated to message
                    // - Comments associated to comment
                    while (associatedComments.size() != 0) {
                        int value = associatedComments.stream().mapToInt(c -> c.getScore()).sum();
                        m.setValue(m.getValue() + value);
                        List<Comment> tempComments = null;
                        for (Comment ac : associatedComments) {
                            int id = ac.getIdComment();
                            tempComments = this.comments.stream().filter(c -> c.getPidComment() == id).collect(Collectors.toList());
                        }
                        associatedComments = tempComments;
                    }
                });

                Comparator<Message> comp = (Message a, Message b) -> {
                    return b.getValue() - a.getValue();
                };
                Collections.sort(this.messages, comp);
                this.bestMessages = this.messages.subList(0, Math.min(this.messages.size(), 3));

                this.bestMessages.stream().forEach(System.out::println);

                // decrease the score of message and comment
                this.messages.stream().forEach(m -> m.setScore(m.getScore() - 1));
                this.comments.stream().forEach(c -> c.setScore(c.getScore() - 1));

                System.out.println("=======");
                Thread.sleep(1000);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
