package tareaextraclase1.tareaextraclase1;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese la IP del servidor: ");
            String serverIP = userInput.readLine();
            System.out.print("Ingrese el puerto del servidor: ");
            int serverPort = Integer.parseInt(userInput.readLine());

            Socket socket = new Socket(serverIP, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            ChatClientHandler clientHandler = new ChatClientHandler(in);
            clientHandler.start();

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

class ChatClientHandler extends Thread {
    private BufferedReader in;

    public ChatClientHandler(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String respuesta;
            while ((respuesta = in.readLine()) != null) {
                System.out.println(respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
