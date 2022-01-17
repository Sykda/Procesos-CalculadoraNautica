package multi.thread.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws Exception {

		// Create welcoming socket at port 2009
		ServerSocket welcomeSocket = new ServerSocket(2009);
		System.out.println("SERVER Conectado al socket de servidor en el puerto: " + welcomeSocket.getLocalPort()
				+ ". Esperando conexiones de clientes.\n");

		while (true) {
			// Se espera y acepta un nuevo cliente
			Socket cliente = welcomeSocket.accept();
			
			// Se instancia una clase para atender al cliente y se lanza en
			// un hilo aparte.
			Thread hiloSesion = new Thread(new Hilo(cliente));
			hiloSesion.start();		

		}
	}
}
