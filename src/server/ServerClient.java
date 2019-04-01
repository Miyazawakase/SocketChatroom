package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClient extends Thread {

	ServerSocket server;

	public static void main(String[] args) {
		new ServerClient().start();
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(12344);
			while (true) {
				Socket socket = server.accept();
				System.out.println("ip:" + socket.getInetAddress() + "连接成功!");
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				ChatManager.getChatManager().vector.add(cs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
