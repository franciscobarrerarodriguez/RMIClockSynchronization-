package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerOperations extends Remote {

	int logIn() throws RemoteException;

	void setAverageTime(String averageTime) throws RemoteException;

	String getAverageTime() throws RemoteException;

	void addHour(String hour) throws RemoteException;

	int getNumberOfClients() throws RemoteException;

	ArrayList<String> getHours() throws RemoteException;
}
