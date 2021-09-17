package chatproject2;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	public ChatWindow(String name, Socket socket, PrintWriter pw, BufferedReader br) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket = socket;
		this.pw = pw;
		this.br = br;
	}

	public void show() {
		/**
		 * 1. UI 초기화
		 */
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfieldr
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
			
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		/**
		 * 3. Chat CLient Thread 생성
		 */
		new ChatClientThread(socket).start();
		
		/**
		 * 2. IOStream 가져오기
		 */
//		try {
////			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
////			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
	
	private void sendMessage() {
		String message = textField.getText();
		pw.println("message:" + message);
		pw.flush();
		textField.setText("");
		textField.requestFocus();
		
		// REceive Thread에서 서버로 부터 받은 베세지가 있다고 치고~
//		updateTextArea("마이콜:" + message);
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		System.out.println("소켓 닫기");
		System.exit(0);
	}
	
	private class ChatClientThread extends Thread {
		private Socket socket;
		
		public ChatClientThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
//			BufferedReader br = null;
			try {
//				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				
				while (true) {
					String data = br.readLine();
					if(data == null) {
						break;
					}
					updateTextArea(data);
//					System.out.println(data);
				}
			} catch(SocketException e) {
				log("suddenly closed by server");
			}
			catch (UnsupportedEncodingException e) {
				log("error: " + e);
			} 
			catch (IOException e) {
				log("error: " + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
					
					if (br != null) {
						br.close();
					}
				} catch (IOException e) {
					log("error: " + e);
				}
			}
			
		}
		
		private void log(String log) {
			System.out.println("[Client] " + log);
		}
			
			
	}
}
