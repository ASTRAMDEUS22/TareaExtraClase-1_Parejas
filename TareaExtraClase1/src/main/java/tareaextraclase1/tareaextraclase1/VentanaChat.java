package tareaextraclase1.tareaextraclase1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class VentanaChat {

    public VentanaChat(){

        parametrosVentana();

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

    //Dirección IP predeterminada
    String IP = "127.0.0.1";

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




        //Agregar elementos al contenedor secundario
        contenedorSecundario.getChildren().addAll(

                cajaTexto,
                botonEnviar,
                areaTexto,
                ipTexto,
                puertoTexto

        );

    }

    private void escribirMensaje(String mensaje){

        //Comprobar si el mensaje esta vacío
        if (!mensaje.isEmpty()){

            areaTexto.appendText(mensaje + "\n");
            cajaTexto.clear();

        }

    }

}
