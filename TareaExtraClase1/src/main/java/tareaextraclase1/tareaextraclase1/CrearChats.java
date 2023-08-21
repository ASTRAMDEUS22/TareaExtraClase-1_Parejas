package tareaextraclase1.tareaextraclase1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.Random.*;

public class CrearChats extends Application {

    //Contenedor principal
    private StackPane contenedorPrincipal = new StackPane();
    private StackPane contenedorSecundario = new StackPane();

    //Botones
    private Button boton_CrearChat = new Button();

    //Lista enlazada para guardar todos los elementos creados
    private LinkedList listaEnlazada = new LinkedList<>();

    //Instancia del random
    Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Diseño y comportamiento de la ventana
        parametrosVentana(primaryStage);

        //Elementos graficos de la ventana
        elementosGraficos();

    }

    private void parametrosVentana(Stage stage){

        Scene scene = new Scene(contenedorPrincipal);
        contenedorPrincipal.getChildren().add(contenedorSecundario);
        stage.setScene(scene);
        stage.setWidth(200);
        stage.setHeight(100);
        stage.show();
        stage.setResizable(false);

    }

    private void elementosGraficos(){

        //Boton para crear las ventanas de chat
        boton_CrearChat.setText("Crear Chat");
        boton_CrearChat.setOnAction(e -> {

            VentanaChat ventana = new VentanaChat(generarPuertoAleatorio());
            ventana.elementosGraficos();


        });

        //Agregar elementos al contenedor secundario
        contenedorSecundario.getChildren().add(boton_CrearChat);

    }

    private int generarPuertoAleatorio(){

        //Generar puerto aleatorio
        int numRan = random.nextInt(30000);

        while (listaEnlazada.contains(numRan)){
            numRan = random.nextInt(30000);
        }

        listaEnlazada.add(numRan);

        return numRan;

    }

}
