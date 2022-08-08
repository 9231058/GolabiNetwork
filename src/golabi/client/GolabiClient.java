package golabi.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GolabiClient {

	private Socket socket;
	private String data = "SALAM";

	public GolabiClient() {
		try {
			socket = new Socket("127.0.0.1", 1373);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(data.getBytes(StandardCharsets.US_ASCII));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
