package logic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import tools.AllConstants;

public class Client implements Runnable {

	private String ip;
	private int port;
	private String typeClient;
	private String averageTime;
	private int numberOfClients;
	private ArrayList<String> hours;
	private Thread thread;
	private boolean flag;

	private IServerOperations iServerOperations;

	public Client(String ip, int port, String typeClient)
			throws RemoteException, MalformedURLException, NotBoundException {
		this.ip = ip;
		this.port = port;
		this.typeClient = typeClient;
		this.averageTime = "";
		this.thread = new Thread(this);
		this.flag = false;
		LocateRegistry.getRegistry(this.port);
		this.iServerOperations = (IServerOperations) Naming
				.lookup("rmi://" + this.ip + ":" + this.port + "/" + AllConstants.RMI_ID);

		switch (this.typeClient) {
		case AllConstants.NORMAL_CLIENT:
			this.logIn();
			this.sendHour();
			break;
		}
	}

	public void logIn() throws RemoteException {
		this.numberOfClients = this.iServerOperations.logIn();
	}

	public void sendHour() throws RemoteException {
		this.iServerOperations.addHour(Hour.systemHour());
	}

	public void review() throws RemoteException {
		this.iServerOperations.getNumberOfClients();
	}

	private void activitiesNormalClient() {
		try {
			if (this.iServerOperations.getNumberOfClients() > this.numberOfClients) {
				this.numberOfClients = this.iServerOperations.getNumberOfClients();
				this.averageTime = "";
				this.flag = true;
				this.sendHour();
			} else if (this.averageTime.equals("")) {
				if (!this.iServerOperations.getAverageTime().equals("")) {
					this.averageTime = this.iServerOperations.getAverageTime();
					this.flag = true;
				}
			}
if(this.flag){
	 System.out.println(this.averageTime);
	 this.flag = false;
}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
	}

	private void activitiesTimeServerClient() {
		try {
			if (this.iServerOperations.getNumberOfClients() > this.numberOfClients) {
				this.hours = this.iServerOperations.getHours();
				this.averageTime = Hour.averageTime(this.hours);
				this.iServerOperations.setAverageTime(this.averageTime);
			}
			System.out.println("Servidor tiempo");
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		while (true) {
			switch (this.typeClient) {
			case AllConstants.NORMAL_CLIENT:
				this.activitiesNormalClient();
				break;
			case AllConstants.TIME_SERVER_CLIENT:
				this.activitiesTimeServerClient();
				break;
			}
		}
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public IServerOperations getiServerOperations() {
		return iServerOperations;
	}

	public void setiServerOperations(IServerOperations iServerOperations) {
		this.iServerOperations = iServerOperations;
	}
}