package br.com.stickerboom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        try {

            String user = "";
            String password = "";
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//orclgrad1.icmc.usp.br:1521/pdb_elaine.icmc.usp.br", user, password);
            System.out.println("Conectou!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        launch();
    }
}