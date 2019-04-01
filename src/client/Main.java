package client;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ChatClientManager.getChatManager().connect();

		textArea = new JTextArea();
		textArea.setText("欢迎进入聊天室!");

		textField = new JTextField();
		textField.setColumns(10);

		button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				textField.setText("");
				appendText("我：" + msg);
				ChatClientManager.getChatManager().send(msg);
			}
		});

		JLabel label = new JLabel("\u6D88\u606F\uFF1A");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
				.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(textArea, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(1)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		contentPane.setLayout(gl_contentPane);
	}

	public void appendText(String str) {
		textArea.append("\n" + str);
	}
}
