package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ServerOperations extends UnicastRemoteObject implements IServerOperations {

	private DateFormat dateHourFormat;

	private int numberOfClients;
	private ArrayList<String> hours;
	private String averageTime;
	private boolean calculate;

	protected ServerOperations() throws RemoteException {
		this.dateHourFormat = new SimpleDateFormat("HH:mm:ss");
		this.numberOfClients = 0;
		this.hours = new ArrayList<>();
	}

	@Override
	public int logIn() throws RemoteException {
		this.averageTime = "";
		this.calculate = false;
		this.hours = null;
		this.hours = new ArrayList<>();
		this.numberOfClients++;
		return this.numberOfClients;
	}

	@Override
	public void addHour(String hour) throws RemoteException {
		this.hours.add(hour);
	}

	@Override
	public int getNumberOfClients() {
		return this.numberOfClients;
	}

	@Override
	public ArrayList<String> getHours() throws RemoteException {
		return this.hours;
	}

	@Override
	public void setAverageTime(String averageTime) throws RemoteException {
		this.averageTime = averageTime;
	}

	@Override
	public String getAverageTime() throws RemoteException {
		return this.averageTime;
	}

	public DateFormat getDateHourFormat() {
		return dateHourFormat;
	}

	public void setDateHourFormat(DateFormat dateHourFormat) {
		this.dateHourFormat = dateHourFormat;
	}

	public boolean isCalculate() {
		return calculate;
	}

	public void setCalculate(boolean calculate) {
		this.calculate = calculate;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public void setHours(ArrayList<String> hours) {
		this.hours = hours;
	}
}