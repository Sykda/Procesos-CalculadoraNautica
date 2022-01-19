package multi.thread.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws Exception {

		String resultado;

		System.out.println("CLIENT: ");

		Socket clientSocket = new Socket("127.0.0.1", 2009);

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce una operación: ");
		String suma = sc.nextLine();

		// Create an output stream attached to the socket
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		// Create an input stream attached to socket
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		if (suma.contains("apagar")) {
			outToServer.writeBytes("apagar");
		} else {

			// Send a line to server
			outToServer.writeBytes(suma + '\n'); // IMPORTANTE '\n' O NO ENVIA NADA, DESDE EL SERVIDOR LO
													// MISMO!!!!!!!!!!!

			// Read a line from server
			resultado = inFromServer.readLine();

			System.out.println("FROM SERVER: " + resultado);

			clientSocket.close();
			sc.close();
		}

	}
}
