package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	private static final int defaultPort = 40008;
	private static final String defaultIP = "localhost";
	static int serverPort;
	static String serverIP;
	
	ServerSocket serverListener;
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	String currentMessage;
	
	public static void main(String[] args) throws Exception {
		
		// Basic deal with args, limited, 1 means setting server port, 2 means setting port and ip, anything else uses defaults
		if (args.length == 1) {
			serverPort = Integer.parseInt(args[0]);
			serverIP = defaultIP;
		} else if(args.length == 2) {
			serverPort = Integer.parseInt(args[0]);
			serverIP = args[1];
		}
		else {
			serverPort = defaultPort;
			serverIP = defaultIP;
		}
		
		System.out.println("Chat Server starting up, using Port: " + serverPort);
		ChatServer chatServer = new ChatServer(serverPort, serverIP);

		// here should be the loop of whatever the server is going to do.
		// will do a test, server should keep running until a client sends the text 'QUIT',
		// when server recives a message, it will display to its console, and send a random number along with the first few
		// letters of the message it was sent back to the client.
		while(chatServer.currentMessage != "QUIT"){
			chatServer.currentMessage = chatServer.in.readLine();
			System.out.println("Client says: " + chatServer.currentMessage);
			
			if(chatServer.currentMessage.equals("QUIT")) {
				chatServer.out.println("QUIT");
			}
			else if(chatServer.currentMessage.length()<=4) {
				chatServer.out.println("TALK MORE! Here is a number! " + Math.random()*100);
			}
			else {
				chatServer.out.println("" + chatServer.currentMessage.substring(0,5) + "" + Math.random()*1000);
			}
			
		}
		System.out.println(chatServer.currentMessage + " Recived, shutting down");
		
		
		System.out.println("Server shutting down.");
		chatServer.in.close();
		chatServer.out.close();
		chatServer.clientSocket.close();
		chatServer.serverListener.close();
		System.out.println("Server shutdown completed.");
	}
	
	public ChatServer(int portNumber, String ip) throws IOException {
		
		this.serverListener = new ServerSocket(portNumber);
		this.clientSocket = new Socket();
		this.currentMessage = "";
		
		this.clientSocket = serverListener.accept();
		System.out.println("Server now waiting for a connections.");
		
		this.out = new PrintWriter(clientSocket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	

}
