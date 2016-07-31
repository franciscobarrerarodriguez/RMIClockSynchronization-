package test;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import logic.Server;

public class MainServer {

	public static void main(String[] args) {
		
		Server server = new Server(1450);
		try {
			server.runServer();
			System.out.println("Server running");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
