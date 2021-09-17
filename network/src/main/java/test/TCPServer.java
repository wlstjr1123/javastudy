package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 1-1 TIME_WAIT 상태에서도 소켓 포트 번호 할당이 가능하도록 하기 위해서..
			serverSocket.setReuseAddress(true);
			
			
			// 2. 바인딩(binding)
			// Socket에 InetSocketADdress(IPAddress + port)
			// IPAdress : 0.0.0.0 : 모든 IP로 부터의 연결을 허용, 특정 호스트 IP바인딩 하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// 클라이언트로 부터 연결 요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");

			try {
				// 4. IO Stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					if (readByteCount == -1) {
						// 클라이언트가 정상적으로 종료(close() 호출)
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] receoved:" + data);
					
					//6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
			} catch (SocketException e) {
				System.out.println("[server] suddenly closed by client" + e);
			} catch (IOException e) {
				System.out.println("[server] error" + e);
			}  finally {
				try {
					if (socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			System.out.println("[Server] error:" + e);
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
