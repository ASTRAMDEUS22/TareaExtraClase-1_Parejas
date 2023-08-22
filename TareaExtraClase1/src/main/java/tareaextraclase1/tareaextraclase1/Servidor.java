package tareaextraclase1.tareaextraclase1;

import java.io.*;
import java.net.*;

public class Servidor {
    /**
     * Método principal que inicia el servidor.
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // Crear un socket de servidor en un puerto aleatorio
            ServerSocket serverSocket = new ServerSocket(0); // Puerto aleatorio
            System.out.println("Servidor de chat en el puerto: " + serverSocket.getLocalPort());
            // Esperar conexiones entrantes y manejarlas en hilos separados
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/**
 * Esta clase maneja la comunicación con un cliente en un hilo separado.
 */
class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Constructor para inicializar el manejador de cliente con un socket.
     * @param socket El socket del cliente.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo del manejador de cliente.
     */
    @Override
    public void run() {
        try {
            // Configurar flujos de entrada y salida con el cliente
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            // Leer mensajes del cliente y responder
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);

                // Leer respuesta desde la consola del servidor
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Respuesta del servidor: ");
                String serverResponse = serverInput.readLine();

                // Enviar respuesta al cliente
                out.println("Respuesta del servidor: " + serverResponse);
            }
            // Cerrar flujos y socket al finalizar
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
