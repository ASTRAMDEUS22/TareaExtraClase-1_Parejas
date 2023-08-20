package tareaextraclase1.tareaextraclase1;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(0); // Puerto aleatorio
            System.out.println("Servidor de chat en el puerto: " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);

                // Leer respuesta desde la consola del servidor
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Respuesta del servidor: ");
                String serverResponse = serverInput.readLine();

                out.println("Respuesta del servidor: " + serverResponse);
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
