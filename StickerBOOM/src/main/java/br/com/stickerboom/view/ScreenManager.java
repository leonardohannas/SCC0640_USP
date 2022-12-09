package br.com.stickerboom.view;

import br.com.stickerboom.entity.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {

    public static final String WINDOW_TITLE = "StickerBOOM";
    public static final short HEIGHT = 500;
    public static final short WIDTH = 600;
    private static final String PATH_LOGIN_REGISTER_SCREEN = "/br/com/stickerboom/view/login_register_screen.fxml";
    private static final String PATH_REGISTER_SCREEN = "/br/com/stickerboom/view/register_screen.fxml";
    private static final String PATH_MAIN_SCREEN_COLLECTOR = "/br/com/stickerboom/view/screen_collector.fxml";
    private static Stage window;
    private static User user;

    private static ScreenManager instance;

    private ScreenManager() {
        window = new Stage();
        window.setTitle(WINDOW_TITLE);
        window.setResizable(false);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ScreenManager.user = user;
    }

    public static void build() {
        if (instance == null)
            instance = new ScreenManager();
    }

    private static void showFXMLScreen(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(path));
        window.setScene(new Scene(loader.load()));
        window.show();
    }

    public static void showLoginRegisterScreen() throws IOException {
       showFXMLScreen(PATH_LOGIN_REGISTER_SCREEN);
    }

    public static void showRegisterScreen() throws IOException {
        showFXMLScreen(PATH_REGISTER_SCREEN);
    }

    public static void showMainScreenCollector() throws IOException {
        showFXMLScreen(PATH_MAIN_SCREEN_COLLECTOR);
    }

    public static void showAlert(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
