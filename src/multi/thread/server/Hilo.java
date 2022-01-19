package multi.thread.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class Hilo implements Runnable {

	String clientSentence;
	Socket connectionSocket;

	public Hilo(Socket connectionSocket) {	
		this.connectionSocket = connectionSocket;
		
	}

	public void run() {
		
		while(true) {

			try {
				// Create input stream, attached to socket
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));

				// Create output stream, attached to socket
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				// Read in line from socket
				clientSentence = inFromClient.readLine();
				if (clientSentence.contains("apagar")){	
					connectionSocket.close();
					
				}else {
				
				StringTokenizer st = new StringTokenizer(clientSentence);
				int x= Integer.parseInt(st.nextToken());
				String operacion= st.nextToken();
				int y= Integer.parseInt(st.nextToken());
				int resultado = 0;
		
				if (clientSentence.contains("+")) {
					resultado= x+y;
				}
				else if (clientSentence.contains("-")) {
					resultado= x-y;
				}
				
				else if (clientSentence.contains("*")) {
					resultado= x*y;
				}
				else if (clientSentence.contains("/")) {
					resultado= x/y;
				}	
									

				String modifiedSentence=String.valueOf(resultado);				
				outToClient.writeBytes(modifiedSentence+ '\n');

				System.out.println("CLIENT Conectado desde: " + connectionSocket.getInetAddress().getHostAddress() + ":"
						+ connectionSocket.getPort());
				System.out.println("SERVER Client sentence: " + clientSentence);
				System.out.println("SERVER Modified sentence: " + resultado+ '\n');
				}
				
				break;
				
				
			} catch (Exception ex) {
				System.out.println("Error: "+ ex.getMessage());
				break;
			}			
			
		}
		
	}

}
