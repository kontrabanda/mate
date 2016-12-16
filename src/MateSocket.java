import java.lang.*;
import java.io.*;  
import java.net.*;
import java.util.*;

public class MateSocket {
	private final int SLEEP_TIME = 100;

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private ArrayList<MateSocketObserver> observersList;

	public MateSocket(String ip, int port) {
		try {
			try {
				socket = new Socket(ip, port);	
			} catch(UnknownHostException e) {
				e.printStackTrace();
			}

			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}

		observersList = new ArrayList<MateSocketObserver>();
	}

	private void startListening() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						String message = (String)inputStream.readUTF();

						if(message != "") {
							System.out.println("Otrzymano wiadomośc!");
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
			}
		}.start();
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
		try {
			System.out.println("Sending message: " + message);
			outputStream.writeUTF(message);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
};