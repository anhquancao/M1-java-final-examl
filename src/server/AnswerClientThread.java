package server;

import models.Message;
import util.Constant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by caoquan on 4/18/17.
 */
public class AnswerClientThread extends Thread {
    private List<Message> bestMessages;
    private String answer;
    private Socket socket;

    public AnswerClientThread(Socket socket, String messages) {
        this.answer = messages;
        this.socket = socket;
    }

    @Override
    public void run() {
        if (answer == null) {
            StringBuilder answer = new StringBuilder();
            for (Message m : bestMessages) {
                answer.append(m.getIdMessage()).append("|").append(m.getIdUser());
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Constant.CHARSET));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Constant.CHARSET));
            // Read command
            String input = reader.readLine();

            if (input.equals(Constant.GET_BEST_MESSAGES)) {
                System.out.println(input);

                // Convert answer message to xml
                JAXBContext contextObj = JAXBContext.newInstance(XMLMessage.class);
                Marshaller marshallerObj = contextObj.createMarshaller();
                marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                XMLMessage message = new XMLMessage(answer);
                marshallerObj.marshal(message, writer);
//                writer.write(answer.toString());
            } else {
                writer.write("Invalid Command");
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}
