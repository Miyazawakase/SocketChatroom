package server;

import java.util.Vector;

public class ChatManager {

	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	private static ChatManager manager = new ChatManager();

	private ChatManager() {
	}

	public static ChatManager getChatManager() {
		return manager;
	}

	public void send(int from, int to, String msg) {
		// 这个先不写
	}

	public void send(ChatSocket from, String msg) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket cs = vector.get(i);
			if (!cs.equals(from)) {
				cs.send(msg);
			}
		}
	}

}
