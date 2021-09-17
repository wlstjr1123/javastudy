package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		String line = "www.douzone.com";
		
		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName(line);
			
			for (int i = 0; i < inetAddresses.length; i++) {
//				inetAddresses[i].
				System.out.println(inetAddresses[i].getCanonicalHostName());
				System.out.println(inetAddresses[i].getHostAddress());
				System.out.println(inetAddresses[i].getHostName());
				System.out.println(inetAddresses[i].getAddress());
				System.out.println(inetAddresses[i].getAllByName("www.naver.com")[0].getHostAddress());
				System.out.println(inetAddresses[i].getAllByName("www.naver.com")[0].getCanonicalHostName());
				System.out.println(inetAddresses[i].getAllByName("www.naver.com")[0].getHostName());
//				System.out.println(inetAddresses[i].);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
