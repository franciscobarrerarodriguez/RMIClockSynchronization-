package test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logic.Client;
import tools.AllConstants;

public class MainClient {

	public static void main(String[] args) {

		Client client;
		try {
			client = new Client("localhost", 1450, AllConstants.NORMAL_CLIENT);
			client.getThread().start();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
