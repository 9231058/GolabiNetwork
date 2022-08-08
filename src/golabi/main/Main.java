package golabi.main;

import golabi.client.GolabiClient;
import golabi.server.GolabiServer;

public class Main {

	public static void main(String[] args) {
		GolabiServer golabiServer = new GolabiServer(1373);
		new Thread(golabiServer).start();
		new GolabiClient();
	}

}
