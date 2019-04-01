package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientManager {

	private static final ChatClientManager cm = new ChatClientManager();
	Socket socket;
	Main main;
	BufferedReader br;
	PrintWriter pw;

	private ChatClientManager() {
	}

	public static ChatClientManager getChatManager() {
		return cm;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void connect() {
		new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket("193.112.103.48", 12344);
					pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

					String line = "";
					while ((line = br.readLine()) != null) {
						main.appendText(socket.getInetAddress() + ":" + line);
					}

					pw.close();
					br.close();
					pw = null;
					br = null;

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void send(String msg) {
		if (pw != null) {
			pw.write(msg + "\n");
			pw.flush();
		}
	}

	public void close() {
		try {
			socket.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
