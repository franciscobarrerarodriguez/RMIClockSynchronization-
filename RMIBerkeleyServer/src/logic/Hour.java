package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tools.AllConstants;

public class Hour {

	public static String systemHour() {
		Calendar fecha = new GregorianCalendar();
		return fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE) + ":" + fecha.get(Calendar.SECOND)
				+ ":" + fecha.get(Calendar.MILLISECOND);
	}

	public static String averageTime(ArrayList<String> hours) {
		double averageHour = 0;
		double averageMinutes = 0;
		double averageSeconds = 0;
		double averageMiliseconds = 0;
		int size = hours.size();
		for (int i = 0; i < hours.size(); i++) {
			String auxiliar[] = hours.get(i).split(":");
			averageHour += Integer.parseInt(auxiliar[0]);
			averageMinutes += Integer.parseInt(auxiliar[1]);
			averageSeconds += Integer.parseInt(auxiliar[2]);
			averageMiliseconds += Integer.parseInt(auxiliar[3]);
		}
		averageHour /= size;
		averageMinutes /= size;
		averageSeconds /= size;
		averageMiliseconds /= size;
		double aux = (Math.round(averageHour) - averageHour);
		if (aux > 0) {
			averageHour = Math.round(averageHour);
			double res = aux * AllConstants.MINUTES;
			averageMinutes += res;
			averageSeconds += res;
			if (averageMinutes == 60) {
				averageMinutes = 0;
			}
			if (averageSeconds == 60) {
				averageSeconds = 0;
			}
		}
		return Math.round(averageHour) + ":" + Math.round(averageMinutes) + ":" + Math.round(averageSeconds) + ":"
				+ Math.round(averageMiliseconds);
	}
}
