import java.lang.*;
import java.io.*;  
import java.net.*;
import java.util.*;

public class MateSocket {
	private final int SLEEP_TIME = 100;

	private Socket socket;
	private BufferedReader inputStream;
	private PrintWriter outputStream;
	private ArrayList<MateSocketObserver> observersList;

	public MateSocket(String ip, int port) {
		try {
			try {
				socket = new Socket(ip, port);	
			} catch(UnknownHostException e) {
				e.printStackTrace();
			}

			outputStream = new PrintWriter(socket.getOutputStream(), true);
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException e) {
			e.printStackTrace();
		}

		observersList = new ArrayList<MateSocketObserver>();
		startListening();
	}

	private void startListening() {
		new Thread() {
			public void run() {
				while(socket.isConnected()) {
					try {
						String message = inputStream.readLine();

						if(message != null) {
							notifyObservers(message);
						}

					} catch(IOException e) {
						e.printStackTrace();
					}

					try {
						Thread.sleep(SLEEP_TIME);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}					
				}

				connectionLostHandler();
			}
		}.start();
	}

	private void connectionLostHandler() {
		//FIXME
		System.out.println("Connection Lost!!!");
	}

	private void notifyObservers(String message) {
		for(MateSocketObserver element : observersList) {
			element.notify(message);
		}
	}

	public void registerObserver(MateSocketObserver observer) {
		observersList.add(observer);
	}

	public void unregisterObserver(MateSocketObserver observer) {
		observersList.remove(observer);
	}

	public void sendMessage(String message) {
		outputStream.println(message);
	}
};