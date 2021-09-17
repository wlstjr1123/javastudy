package chatproject;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
	private static final int PORT = 6000;
	
	public static void main(String[] args) {
		ServerSocket serverScoket = null;
		List<Writer> listWriters = new ArrayList<>();
		
		try {
			serverScoket = new ServerSocket();
			
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverScoket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("연결 기다림 " + hostAddress + ":" + PORT);
			
			while (true) {
				Socket socket = serverScoket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
			
		} catch(SocketException e) {
			log("suddenly closed by server");
		}
		catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (serverScoket != null && !serverScoket.isClosed()) {
					serverScoket.close();
				}
				
			} catch (IOException e) {
				log("error: " + e);
			}
		}
	}
	
	public static void log(String log) {
		System.out.println("[ChatServer] " + log);
	}
}
