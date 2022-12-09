package br.com.stickerboom.view;

import br.com.stickerboom.database.Queries;
import br.com.stickerboom.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Método que controla a tela login_register_screen, onde o usuário do sistema faz login ou se registra no sistema
 * @since dec 2022
 */
public class LoginScreenController {

    @FXML
    private TextField cpfField;
    private String cpfValue;

    @FXML
    private Text errorText;

    @FXML
    private Button registerButton;

    /**
     * Método que valida, recupera os dados de um colecionador e entra na tela screen_collector
     * @since dec 2022
     * @param actionEvent Evento do botão de Login
     */
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

    /**
     * Método que direciona o usuário a tela "register_screen"
     * @since dec 2022
     * @param actionEvent Evento do botão "Registrar" usuário
     * @throws IOException Falha ao abrir a tela de registro do usuário
     */
    public void onRegisterButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenManager.showRegisterScreen();
    }

    /**
     * Método que faz a formatação e tratamento dos dados inseridos pelo usuário no campo "CPF"
     * @since dec 2022
     * @param keyEvent Evento acionado quando uma tecla do keyboard é liberada
     */
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
