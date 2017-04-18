package client;

import util.Constant;

import java.io.*;
import java.net.Socket;

/**
 * Created by caoquan on 4/18/17.
 */
public class Client {
    private Socket socket;

    private BufferedWriter writer;
    private BufferedReader reader;

    protected int port;


    public Client() {
        this.port = Constant.PORT;
    }

    public void connect() {
        try {
            this.socket = new Socket("localhost", this.port);
            InputStream inputStream = socket.getInputStream();
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Constant.CHARSET));
            this.reader = new BufferedReader(new InputStreamReader(inputStream, Constant.CHARSET));


            this.writer.write("GET_BEST_MESSAGES");
            this.writer.newLine();
            this.writer.flush();

            int attempts = 0;
            while (inputStream.available() == 0 && attempts < 1000) {
                attempts++;
                Thread.sleep(10);
            }
            if (attempts == 1000) {
                System.out.println("Request Timeout");
            }
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                String line = reader.readLine();
                System.out.println(line);
                result.append(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        Client client = new Client();
        client.connect();
    }

}
