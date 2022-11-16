package br.com.stickerboom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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

            String user = "a11800559";
            String password = "stickerboom";
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//orclgrad1.icmc.usp.br:1521/pdb_elaine.icmc.usp.br", user, password);
            System.out.println("Conectou!");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CARGO");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "| " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        launch();
    }
}