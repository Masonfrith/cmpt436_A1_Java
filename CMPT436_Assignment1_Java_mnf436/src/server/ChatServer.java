package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	ServerSocket serverSocket;
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	
	
	public ChatServer() {
		try {
			serverSocket = new ServerSocket(8002);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ChatServer(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
