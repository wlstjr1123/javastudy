package chatproject2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatClientApp {
	
	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);
		ChatWindow window = null;

		while (true) {

			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();

			if (name.isEmpty() == false) {
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		scanner.close();

		Socket socket = null;

		try {
			// 1. create socket
			socket = new Socket();
			// 2. connetn to server
			socket.connect(new InetSocketAddress("127.0.0.1", 6000));
			
			// 3. get iostream
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			window = new ChatWindow(name, socket, pw, br);
			
			pw.println("join:" + name);
			pw.flush();
			
			// 4. join protocol
				String line = br.readLine();
				
				if ("join.ok".equals(line)) {
//					br.close();
//					pw.
					window.show();
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scanner.close();
		}

//		new ChatWindow(name).show();
	}

}
