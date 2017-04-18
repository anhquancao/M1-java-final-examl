package server;

import util.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caoquan on 4/18/17.
 */
public class Server {
    ExecutorService service;
    private ServerSocket serverSocket;
    private ComputeBestMessageThread computeBestMessageThread;

    public Server(int port) {
        service = Executors.newFixedThreadPool(5);
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening at port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        computeBestMessageThread = new ComputeBestMessageThread("reseauSocial.txt");
//        service.submit(computeBestMessageThread);
    }

    public String rmiGetBestMessages() {
        try {
            Registry registry = LocateRegistry.getRegistry(Constant.HOST, Constant.RMI_PORT);
            ComputeMessageRMI computeMessageRMI = (ComputeMessageRMI) registry.lookup("ComputeMessageRMI");
//            List<Message> bestMessages = computeMessageRMI.getBestMessages();
//            bestMessages.stream().forEach(System.out::println);
            String bestMessages = computeMessageRMI.getBestMessages();
            return bestMessages;
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listen() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
//                List<Message> bestMessages = this.computeBestMessageThread.getBestMessages();

                // Call to RMI method
                String bestMessages = rmiGetBestMessages();
                AnswerClientThread answerClientThread = new AnswerClientThread(socket, bestMessages);
                this.service.submit(answerClientThread);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(Constant.PORT);
        server.listen();
    }
}
