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
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	
	public static void main(String[] args) {
		
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
		
		
		
		
	}
	
	public ChatServer() {
		
	}
	
	

}
