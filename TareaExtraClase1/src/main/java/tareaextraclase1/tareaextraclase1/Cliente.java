package tareaextraclase1.tareaextraclase1;

import java.io.*;
import java.net.*;

public class Cliente {
    /**
     * Método principal que inicia el cliente.
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // Configuración de entrada de usuario
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            // Solicitar al usuario la dirección IP del servidor
            System.out.print("Ingrese la IP del servidor: ");
            String serverIP = userInput.readLine();
            // Solicitar al usuario el puerto del servidor
            System.out.print("Ingrese el puerto del servidor: ");
            int serverPort = Integer.parseInt(userInput.readLine());
            // Establecer la conexión con el servidor

            Socket socket = new Socket(serverIP, serverPort);
            // Configuración de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Iniciar un hilo para manejar las respuestas del servidor
            ChatClientHandler clientHandler = new ChatClientHandler(in);
            clientHandler.start();

            // Loop principal para enviar mensajes al servidor
            while (true) {
                System.out.print("Escriba un mensaje: ");
                String mensaje = userInput.readLine();
                out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/**
 * Esta clase maneja las respuestas del servidor en un hilo separado.
 */
class ChatClientHandler extends Thread {
    private BufferedReader in;
    /**
     * Constructor para inicializar el manejador con un flujo de entrada.
     * @param in Flujo de entrada desde el servidor.
     */
    public ChatClientHandler(BufferedReader in) {
        this.in = in;
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo.
     */
    @Override
    public void run() {
        try {
            String respuesta;
            // Leer y mostrar las respuestas del servidor
            while ((respuesta = in.readLine()) != null) {
                System.out.println(respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
