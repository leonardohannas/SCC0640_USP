package br.com.stickerboom.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {

    public static final short HEIGHT = 400;
    public static final short WIDTH = 600;

    private static final String PATH_LOGIN_REGISTER_SCREEN = "/br/com/stickerboom/login_register_screen.fxml";

    private static Stage window;

    private static ScreenManager instance;

    private ScreenManager() {
        window = new Stage();
        window.setResizable(false);
        window.setWidth(WIDTH);
        window.setHeight(HEIGHT);
    }

    public static void build() {
        if (instance == null)
            instance = new ScreenManager();
    }

    public static void showLoginRegisterScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(PATH_LOGIN_REGISTER_SCREEN));
        Scene scene = new Scene(loader.load());
        window.setScene(scene);
        window.show();
    }
}
