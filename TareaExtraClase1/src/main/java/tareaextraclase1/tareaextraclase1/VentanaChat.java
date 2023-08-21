package tareaextraclase1.tareaextraclase1;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class VentanaChat implements Runnable {

    /**
     * Constructor de la clase.
     * Constructor de la clase que se encarga de llamar a la configuración de los parámetros de la ventana.
     */
    public VentanaChat(int puerto){

        parametrosVentana();
        this.puerto = puerto;
        Thread thread = new Thread(this);
        thread.start();

    }

    //Contenedor principal
    private StackPane contenorPrincipal = new StackPane();

    //Contenedor secundario
    private StackPane contenedorSecundario = new StackPane();

    //Caja de texto
    private TextField cajaTexto = new TextField();
    private TextField ipTexto = new TextField();
    private TextField puertoTexto = new TextField();

    //Botones
    private Button botonEnviar = new Button();

    //Area de texto
    private TextArea areaTexto = new TextArea();

    //Label
    Label labelPuerto = new Label();

    //Numero random
    private Random random = new Random();

    //Puerto asignado en el server socket
    private int puerto;

    /**
     * Método que se encarga de configurar las caracteristicas y comportamientos de la ventana.
     * Se crea una Scene a la que se le asigna el contenedor StackPane, asi mismo se crea una Stage a la que se le
     * asigna la Scene anteriormente creada, se asigna el tamaño de la ventana, el cambio de tamaño manual se desactiva,
     * finalmente se muestra la ventana al usuario.
     */
    private void parametrosVentana(){

        Scene scene = new Scene(contenorPrincipal);
        contenorPrincipal.getChildren().add(contenedorSecundario);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Método encargado de manejar los distintos componentes de la interfaz.
     * Este método se encarga de editar las caracteristicas de los distintos componentes gráficos para darles posición,
     * tamaño y un comportamiento específico.
     * La "caja de texto" se encarga de recibir el mensaje que el usuario desea enviar a otro.
     * El "ip texto" se encarga de enviar el mensaje a una dirección IP en específico, a su lado el "puerto texto"
     * redirige el mensaje a un puerto específico de la dirección IP asignada.
     * El "area de texto" se usa para mostrar al usuario los mensajes recibidos desde otro usuario.
     * El "boton enviar" es usado para enviarle a un usuario es específico por medio de la IP y el puerto el mensaje
     * escrito en la "caja de texto".
     */
    public void elementosGraficos(){

        //Caja de texto mensajes
        cajaTexto.setMaxWidth(275);
        cajaTexto.setTranslateX(-35);
        cajaTexto.setTranslateY(250);

        //Caja de texto IP
        ipTexto.setTranslateX(-135);
        ipTexto.setTranslateY(-250);
        ipTexto.setMaxWidth(85);
        ipTexto.setPromptText("IP");

        //Caja de texto Puerto
        puertoTexto.setTranslateX(-35);
        puertoTexto.setTranslateY(-250);
        puertoTexto.setMaxWidth(85);
        puertoTexto.setPromptText("Puerto");


        //Boton enviar mensaje
        botonEnviar.setText("Enviar");
        botonEnviar.setTranslateX(150);
        botonEnviar.setTranslateY(250);
        botonEnviar.setOnAction(e -> escribirMensaje(cajaTexto.getText()));

        //Area de texto
        areaTexto.setMaxWidth(350);
        areaTexto.setMaxHeight(450);
        areaTexto.setEditable(false);

        //Label del puerto
        labelPuerto.setText("Puerto local: " + puerto);
        labelPuerto.setTranslateX(100);
        labelPuerto.setTranslateY(-250);


        //Agregar elementos al contenedor secundario
        contenedorSecundario.getChildren().addAll(

                cajaTexto,
                botonEnviar,
                areaTexto,
                ipTexto,
                puertoTexto,
                labelPuerto

        );

    }

    /**
     * Método encargado de escribir el mensaje del usuario en el "area de texto" para poder observar lo enviado.
     * El método utiliza el parámetro que recibe y lo escribe en el "area de texto", si el mensaje viene vacío, no lo
     * escribe y sale del método.
     * @param mensaje String que contiene el mensaje guardado en la "caja de texto".
     */
    private void escribirMensaje(String mensaje){

        //Comprobar si el mensaje esta vacío
        if (mensaje.isEmpty()) {
            return;
        }

        //Comprobar si el puerto esta vacío
        if (puertoTexto.getText().isEmpty()) {
            return;
        }

        //Comprobar si la Ip esta vacía
        if (ipTexto.getText().isEmpty()) {
            ipTexto.setText("127.0.0.1");
        }

        areaTexto.appendText("Tú: " + mensaje + "\n");
        cajaTexto.clear();

        //Escribir el mensaje
        enviarMensajeServer(mensaje,ipTexto.getText(),Integer.parseInt(puertoTexto.getText()));



    }

    private void enviarMensajeServer(String mensaje,String IP, int Puerto){

        try {
            //System.out.println("a");
            Socket socket = new Socket(IP,Puerto);
            //System.out.println("b");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            //System.out.println("c");
            out.println(mensaje);
            //System.out.println("d");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void escribirRespuesta(String respuesta, String direccionIP){

        Platform.runLater(() -> {

            areaTexto.appendText(direccionIP + ": " + respuesta + "\n");

        });


    }

    @Override
    public void run() {

        try {

            ServerSocket serverSocket = new ServerSocket(puerto);
            Socket socket;

            while (true) {

                //El socket tendrá el valor al recibido por el server
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

                //Se lee la información del Socket
                String respuesta = in.readLine();

                //Se llama al método para administrar las respuestas
                escribirRespuesta(respuesta,socket.getInetAddress().getHostAddress());

                //Cerrar sockets
                socket.close();
                inputStream.close();
                in.close();
            }

            } catch(IOException e){
                throw new RuntimeException(e);
            }


    }
}
