package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int PORT = 6000;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("starts... [port:" + PORT + "]");

			Socket socket = serverSocket.accept();

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");
			
			try {
				// InputStream in = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true); // true = 쓸때마다 자동 flush

				while (true) {
					String data = br.readLine();

					if (data == null) {
						log("closed by client");
						break;
					}
					
					log("received:" + data);
					
					pw.println(data);
				}
			} catch (IOException e) {
				log("error:" + e);
			} finally {
				try {
					if (socket != null && socket.isClosed() == false) {
						socket.close();
					}

				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}

	}

	public static void log(String log) {
		System.out.println("[EchoServer] " + log);
	}

}
