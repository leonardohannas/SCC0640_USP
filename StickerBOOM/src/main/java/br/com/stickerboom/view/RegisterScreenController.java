package br.com.stickerboom.view;

import br.com.stickerboom.database.Queries;
import br.com.stickerboom.entity.Collector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class RegisterScreenController {

    @FXML
    private Button returnButton;
    @FXML
    private TextField cpfField;
    private String cpfValue;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    public void cpfKeyReleased(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT)
            return;

        int caret = cpfField.getCaretPosition();

        if (keyEvent.getCode() == KeyCode.BACK_SPACE && cpfField.getText().length() > 3)
            caret--;

        if (!cpfField.getText().matches("\\d")) {

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

    public void nameKeyReleased(KeyEvent keyEvent) {

        int caret = nameField.getCaretPosition() - 1;
        String sFormatted = nameField.getText();

        int cutString;
        int tamCurrent = sFormatted.length();
        if(tamCurrent > 30) {
            cutString = tamCurrent - 30;
            sFormatted = sFormatted.substring(0, tamCurrent - cutString);
            nameField.setText(sFormatted);
            nameField.positionCaret(caret);
        }

        if (!nameField.getText().matches("[a-z A-Z á-ú Á-Ú ç Ç]+")) {
            sFormatted = sFormatted.replaceAll("[^(a-z A-Z á-ú Á-Ú  ç Ç)]+", "");

            nameField.setText(sFormatted);
            nameField.positionCaret(caret);
        } else {
            sFormatted = sFormatted.toUpperCase();
        }
    }

    public void addressKeyReleased(KeyEvent keyEvent) {
        int caret = caret = addressField.getCaretPosition();
        String sFormatted = addressField.getText();

        int cutString;
        int tamCurrent = sFormatted.length();
        if(tamCurrent > 50) {
            cutString = tamCurrent - 50;
            sFormatted = sFormatted.substring(0, tamCurrent - cutString);
            addressField.setText(sFormatted);
            addressField.positionCaret(caret);
        }

        if(!addressField.getText().matches("[\\w]+")) {
            sFormatted = sFormatted.replaceAll("@", "");

            addressField.setText(sFormatted);
            addressField.positionCaret(caret);
        } else {
            sFormatted = sFormatted.toUpperCase();
        }
    }

    public void buttonReturnScreen(ActionEvent actionEvent) throws IOException {
        ScreenManager.showLoginRegisterScreen();
//        try{
//            Stage currentScreen = (Stage) returnButton.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("login_register_screen.fxml"));
//            Scene scene = new Scene(root);
//            currentScreen.setScene(scene);
//            currentScreen.show();
//        } catch(IOException e) {
//            Alert message = new Alert(Alert.AlertType.ERROR);
//            message.setContentText(e.getMessage());
//            message.setHeaderText(null);
//            message.showAndWait();
//        }
    }

    public void onActionRegister(ActionEvent actionEvent) {

        String cpf, name, address;

        cpf = cpfField.getText();
        name = nameField.getText();
        address = addressField.getText();

        if (cpf != null && cpf.length() == 14
            && name != null && name.length() > 0
            && address != null && address.length() > 0) {

            int result = Queries.insertCollector(cpf, name, address);
            Collector collector = (Collector) Queries.getUser(cpf);
            ScreenManager.setUser(collector);

            try {
                ScreenManager.showMainScreenCollector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

