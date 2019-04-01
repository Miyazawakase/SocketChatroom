package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {

	Socket socket;
	BufferedReader br;
	PrintWriter pw;

	public ChatSocket(Socket socket) {
		this.socket = socket;
	}

	public void send(String str) {
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			pw.write(str + "\n");
			pw.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println("ip:" + socket.getInetAddress() + "想对大家说:" + line);
				ChatManager.getChatManager().send(this, line);
			}
			socket.shutdownInput();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
