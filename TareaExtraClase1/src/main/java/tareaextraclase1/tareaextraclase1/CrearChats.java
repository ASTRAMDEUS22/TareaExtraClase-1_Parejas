package tareaextraclase1.tareaextraclase1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class CrearChats extends Application {

    //Contenedor principal
    private StackPane contenedorPrincipal = new StackPane();
    private StackPane contenedorSecundario = new StackPane();

    //Botones
    private Button boton_CrearChat = new Button();



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
        boton_CrearChat.setOnAction(e -> new VentanaChat().elementosGraficos());

        //Agregar elementos al contenedor secundario
        contenedorSecundario.getChildren().add(boton_CrearChat);

    }

}
