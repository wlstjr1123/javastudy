package chatproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import echo.EchoServer;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters = new ArrayList<>();
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter pw = null;
//		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
//		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
//		int remoteHostPort = inetRemoteSocketAddress.getPort();
//		log("connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			
			
			while (true) {
				String request = br.readLine();
				if (request == null) {
					log("클라이언트로 부터 연결 끊김");
					break;
				}
				
				String[] tokens = request.split(":");
				
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
				}
			}
		} catch(SocketException e) {
			log("suddenly closed by server");
			doQuit(pw);
		}  
		catch (IOException e) {
			log("클라이언트로 부터 연결 끊김");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				
				if (pw != null) {
					pw.close();
				}
				
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
				
			} catch (IOException e) {
				log("error:" + e);
			}
		}
		
		
	}
	
	private void doQuit(Writer pw) {
		removeWriter(pw);
		
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer pw) {
		listWriters.remove(pw);
		
	}

	private void doMessage(String data) {
		broadcast("[" + nickname + "]" + ":" + data);
		
	}

	private void doJoin(String nickName, PrintWriter pw) {
		this.nickname = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);
		
		addWriter(pw);
		
		
		pw.println("join.ok");
		pw.flush();
		
	}
	
	private void addWriter(PrintWriter pw) {
		synchronized (pw) {
			listWriters.add(pw);
		}
		
	}

	private void broadcast(String data) {
		synchronized (data) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	public static void log(String log) {
		System.out.println("[ChatServer] " + log);
	}
	
}
