package Main;

import server.ChatServer;

public class Main {


	public static void main(String[] args) {
		
		System.out.println("Start the ChatServer.");
		ChatServer server = new ChatServer(40008);

	}

}
