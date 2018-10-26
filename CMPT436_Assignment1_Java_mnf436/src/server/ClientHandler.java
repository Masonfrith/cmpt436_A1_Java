package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	String messageFromClient = null;
	String messageToClient = null;
	Boolean receivedDisconnectCommand = false;
	

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
	}

	@Override
	public void run() {
		
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ClientHandler connected and set up for client");
		
		this.messageFromClient = "";
		this.receivedDisconnectCommand = false;
		while(!this.receivedDisconnectCommand) {
			try {
				this.messageFromClient = this.in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//display the message received from the client
			System.out.println("Client says: " + this.messageFromClient);
			
			// evaluate the message from the client, generates response to send to client
			this.messageToClient = this.evaluateResponse(this.messageFromClient);
			
			// send response to client
			System.out.println("Handler: " + this.messageToClient);
			this.out.println(this.messageToClient);
			
		} // end of while loop
		
		System.out.println("Termination request: " + this.messageFromClient + " Received, attempting to shutdown connection");;
		try {
			this.shutDownHandler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String evaluateResponse(String msg) {
		
		// is the message the command to quit the chat?
		if(msg.equals("QUIT")) {
			this.receivedDisconnectCommand = true;
			return "QUIT";
		}
		// when message is 'short' use this
		else if(msg.length()<=4) {
			return ("TALK MORE! Here is a number! " + Math.round(Math.random()*100));
		}
		// message is longer, so do this
		else {
			return ("" + msg.substring(0,5) + "" + Math.round(Math.random()*1000));
		}
	}
	
	private void shutDownHandler() throws IOException {
		try {
			System.out.println("Server shutting down.");
		}
		finally {
			this.in.close();
			this.out.close();
			this.clientSocket.close();
		}
		System.out.println("ClientHandler shutdown completed.");
	}

}
