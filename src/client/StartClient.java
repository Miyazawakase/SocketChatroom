package client;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							ChatClientManager.getChatManager().close();
						}
					});
					ChatClientManager.getChatManager().setMain(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
