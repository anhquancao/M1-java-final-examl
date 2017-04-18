package server;

import models.Message;
import util.Constant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by caoquan on 4/18/17.
 */
public class RMIServer implements ComputeMessageRMI {
    private ComputeBestMessageThread thread;

    public RMIServer() {
        thread = new ComputeBestMessageThread("reseauSocial.txt");
        thread.start();
    }

    @Override
    public String getBestMessages() throws RemoteException {
        StringBuilder answer = new StringBuilder();
        for (Message m : thread.getBestMessages()) {
            answer.append(m.getIdMessage()).append("|").append(m.getIdUser());
        }

        return answer.toString();
    }

    public static void main(String args[]) {

        try {
            RMIServer server = new RMIServer();
            ComputeMessageRMI computeMessageRMI = (ComputeMessageRMI) UnicastRemoteObject.exportObject(server, Constant.RMI_PORT);

            // Bind the remote object in the registry
            Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
            registry.bind("ComputeMessageRMI", computeMessageRMI);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
