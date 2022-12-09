package br.com.stickerboom.view;

import br.com.stickerboom.database.Queries;
import br.com.stickerboom.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController {

    @FXML
    private TextField cpfField;
    private String cpfValue;

    @FXML
    private Text errorText;

    @FXML
    private Button registerButton;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        String cpf = cpfField.getText();
        User user = Queries.getUser(cpf);
        if (user != null) {
            ScreenManager.setUser(user);
            try {
                ScreenManager.showMainScreenCollector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onRegisterButtonClick(ActionEvent actionEvent) {
        try{
            Stage currentScreen = (Stage) registerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("register_screen.fxml"));
            Scene scene = new Scene(root);
            currentScreen.setScene(scene);
            currentScreen.show();
        } catch(IOException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText(e.getMessage());
            message.setHeaderText(null);
            message.showAndWait();
        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {

        if(keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT)
            return;

        int caret = cpfField.getCaretPosition();

        if(keyEvent.getCode() == KeyCode.BACK_SPACE && cpfField.getText().length() > 3)
            caret--;

        if(!cpfField.getText().matches("\\d")) {

            String sFormatted = cpfField.getText().replaceAll("\\D", "");

            if (sFormatted.length() > 11) // 11 digitos CPF
                sFormatted = cpfValue;

            cpfValue = sFormatted;

            if (sFormatted.length() > 9) {
                sFormatted = sFormatted.substring(0, 3) + "." +
                        sFormatted.substring(3, 6) + "." +
                        sFormatted.substring(6, 9) + "-" +
                        sFormatted.substring(9);
                caret++;
            } else if (sFormatted.length() > 6) {
                sFormatted = sFormatted.substring(0, 3) + "." +
                        sFormatted.substring(3, 6) + "." +
                        sFormatted.substring(6);
                caret++;
            } else if (sFormatted.length() > 3) {
                sFormatted = sFormatted.substring(0, 3) + "." +
                        sFormatted.substring(3);
                caret++;
            }

            cpfField.setText(sFormatted);
            cpfField.positionCaret(caret);
        }
    }
    public void onContextMenuRequested(ContextMenuEvent contextMenuEvent) {

    }

}
