package logic;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import tools.AllConstants;

public class Server {

	private int port;

	private ServerOperations serverOperations;
	private Registry registry;

	public Server(int port) {
		this.port = port;
	}

	public void runServer() throws RemoteException, MalformedURLException, AlreadyBoundException {
		this.serverOperations = new ServerOperations();
		this.registry = LocateRegistry.createRegistry(this.port);
		this.registry.bind(AllConstants.RMI_ID, this.serverOperations);
	}

	public static String findIp() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServerOperations getServerOperations() {
		return serverOperations;
	}

	public void setServerOperations(ServerOperations serverOperations) {
		this.serverOperations = serverOperations;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}
}
