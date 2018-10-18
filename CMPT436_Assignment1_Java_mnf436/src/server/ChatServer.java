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
	ServerSocket serverSocket;
	static PrintWriter out;
	static BufferedReader in;
	
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
		ServerSocket serverListener = new ServerSocket(serverPort);
		Socket clientSocket = new Socket();
		
		System.out.println("Server now waiting for a connection.");
		clientSocket = serverListener.accept();
		
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		//while (some var != some qutting text) keep waiting for input, then repeat the input with some mod
		String textTest = in.readLine();
		System.out.println("Server has recived initial message " + textTest + " from client upon connection");
		
		in.close();
		out.close();
		clientSocket.close();
		serverListener.close();
	}
	
	public ChatServer() {
		
	}
	
	

}
