package golabi.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GolabiClientHandler implements Runnable {
	private Socket socket;
	private String prefix = "HANDLER::";
	private String data = "SALAM";

	public GolabiClientHandler(Socket socket) {
		this.socket = socket;
		prefix = prefix + " " + socket.getLocalAddress() + " : "
				+ socket.getPort() + " ";
	}

	@Override
	public void run() {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			System.out.println(prefix + "Establishing I/O streams ...");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			System.out.println(prefix + "I/O streams established ...");
			System.err.println(inputStream.getClass().toString());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		trasferData(inputStream, outputStream);
		try {
			System.out.println(prefix + "END");
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private void trasferData(InputStream inputStream, OutputStream outputStream) {
		try {
			outputStream.write(data.getBytes(StandardCharsets.US_ASCII));
			byte[] data = new byte[64];
			inputStream.read(data);
			System.out.println(prefix
					+ new String(data, StandardCharsets.US_ASCII));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
