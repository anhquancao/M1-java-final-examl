package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by caoquan on 4/18/17.
 */
public interface ComputeMessageRMI extends Remote {
    String getBestMessages() throws RemoteException;
}
