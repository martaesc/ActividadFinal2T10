package org.example.actividadfinal2t10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende de Application.
 * Esta clase es responsable de cargar la vista inicial de la aplicación y mostrarla en la ventana principal.
 */
public class HelloApplication extends Application {

    /**
     * Método de inicio que se llama después de que se ha inicializado el sistema.
     * Este método carga la vista de inventario desde un archivo FXML y la muestra en la ventana principal.
     *
     * @param stage el escenario principal para esta aplicación, en el que se puede establecer la escena.
     * @throws IOException si el archivo FXML no se puede cargar.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inventario-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 638,400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args argumentos de línea de comando.
     */
    public static void main(String[] args) {
        launch();
    }
}