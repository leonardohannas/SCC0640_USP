package br.com.stickerboom.view;

import br.com.stickerboom.database.CommonQueries;
import br.com.stickerboom.entity.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @FXML
    private TextField cpfField;
    private String cpfValue;

    @FXML
    private Text errorText;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        String cpf = cpfField.getText();
        User user = CommonQueries.getUser(cpf);
        System.out.println(user);
    }

    public void onRegisterButtonClick(ActionEvent actionEvent) {

    }

    private ChangeListener<String> onTextChangeCPFField = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

            System.out.println("s: " + s);
            System.out.println("t1: "+ t1);

            if (!t1.matches("\\d")) {

                String sFormatted = t1.replaceAll("\\D", "");

                if (sFormatted.length() > 11) // 11 digitos CPF
                    sFormatted = cpfValue;

                cpfValue = sFormatted;

                if (sFormatted.length() > 9) {
                    sFormatted = sFormatted.substring(0, 3) + "." +
                            sFormatted.substring(3, 6) + "." +
                            sFormatted.substring(6, 9) + "-" +
                            sFormatted.substring(9);
                } else if (sFormatted.length() > 6) {
                    sFormatted = sFormatted.substring(0, 3) + "." +
                            sFormatted.substring(3, 6) + "." +
                            sFormatted.substring(6);
                } else if (sFormatted.length() > 3) {
                    sFormatted = sFormatted.substring(0, 3) + "." +
                            sFormatted.substring(3);
                }

                cpfField.setText(sFormatted);
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cpfField.textProperty().addListener(onTextChangeCPFField);
    }
}
