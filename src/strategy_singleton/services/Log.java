package strategy_singleton.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

	private static Log INSTANCE = null;
	private static FileWriter filewriter = null;
	private static PrintWriter printwriter = null;
	
	
	public FileWriter getFilewriter() {
		return filewriter;
	}

	public PrintWriter getPrintwriter() {
		return printwriter;
	}

	private Log() {

		try {
			Log.filewriter = new FileWriter("betitomelhoradolog.txt", true);
		} catch (IOException e) {
			System.out.println("Writing will have no effect");
		}

		Log.printwriter = new PrintWriter(Log.filewriter);
	}

	public static Log getInstance() {
		if (INSTANCE == null) {
			synchronized (Log.class) {
				if (INSTANCE == null) {
					INSTANCE = new Log();
				}
			}
			INSTANCE = new Log();
		}
		return INSTANCE;

	}


}
