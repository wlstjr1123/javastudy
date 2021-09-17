package chatproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			while (true) {
				String data = br.readLine();
				if(data == null) {
					break;
				}
				
				System.out.println(data);
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
	
	private static void log(String log) {
		System.out.println("[Client] " + log);
	}
	
	
}
