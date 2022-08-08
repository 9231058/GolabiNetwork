package golabi.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GolabiServer implements Runnable {
	private ServerSocket serverSocket;
	private volatile boolean isAlive;
	private String prefix = "SERVER::";

	public GolabiServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			isAlive = true;
		} catch (IOException exception) {
			isAlive = false;
			exception.printStackTrace();
		}
	}

	public GolabiServer(int port, int backlog, InetAddress inetAddress) {
		try {
			serverSocket = new ServerSocket(port, backlog, inetAddress);
			isAlive = true;
		} catch (IOException exception) {
			isAlive = false;
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				System.out.println(prefix + "Wating for coonection...");
				Socket socket = serverSocket.accept();
				System.out.println(prefix + "Connection accepted ...");
				System.out.println(prefix
						+ "Wait for client handler creation ...");
				GolabiClientHandler clientHandler = new GolabiClientHandler(
						socket);
				System.out.println(prefix + "Starting client handler ...");
				new Thread(clientHandler).start();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	public void shutdown() {
		try {
			serverSocket.close();
			isAlive = false;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
