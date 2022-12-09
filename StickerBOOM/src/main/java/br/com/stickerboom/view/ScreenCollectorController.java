package br.com.stickerboom.view;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.database.Queries;
import br.com.stickerboom.entity.Collector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe que controla a tela screen_collector, possui campos para a busca dos álbuns e informações do usuário
 * @since dec 2022
 * @see javafx.fxml.Initializable
 */
public class ScreenCollectorController implements Initializable {

    @FXML
    private ListView<Album> listViewAlbums;
    private List<Album> listS;

    @FXML
    private Text titleSearchPage;

    @FXML
    private TextField searchTitleField;

    @FXML
    private TextField searchISBNField;

    @FXML
    private Label collectorNameLabel;

    @FXML
    private Label reputationCollectorLabel;

    @FXML
    private HBox availableAlbums;

    @FXML
    private HBox myAlbums;

    /**
     * Método privado
     */
    private enum Window {

        AVAILABLE_ALBUMS {
            @Override
            public String getTitle() {
                return "Álbuns Disponíveis";
            }
        },
        MY_ALBUMS {
            @Override
            public String getTitle() {
                return "Meus Álbuns";
            }
        };

        public abstract String getTitle();
    }

    private Window currentWindow = Window.AVAILABLE_ALBUMS;

    /**
     * Método que inicializa a screen_collector, indica os dados do usuário logado, os álbuns disponíveis e os álbuns
     * que o colecionador já possui
     * @since dec 2022
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        collectorNameLabel.setText(ScreenManager.getUser().getName());
        reputationCollectorLabel.setText("Reputação: " + Float.toString(((Collector) ScreenManager.getUser()).getReputation()));
        titleSearchPage.setText(currentWindow.getTitle());

        listViewAlbums.setCellFactory(new AlbumCellFactory());

        availableAlbums.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY
                    && currentWindow != Window.AVAILABLE_ALBUMS)
                changeWindow();
        });

        myAlbums.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY
                    && currentWindow != Window.MY_ALBUMS)
                changeWindow();
        });

        listS = Queries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        updateListView();
    }

    /**
     * Método que delimita a quantidade de caracteres que podem ser inseridos no campo "Título" pelo usuário do sistema
     * @since dec 2022
     * @param keyEvent Evento acionado quando uma tecla do keyboard é liberada
     */
    public void titleKeyReleased(KeyEvent keyEvent) {

        int caret = searchTitleField.getCaretPosition() - 1;
        String sTitle = searchTitleField.getText();

        int cutString;
        int tamCurrent = sTitle.length();
        if(tamCurrent > 30) {
            cutString = tamCurrent - 30;
            sTitle = sTitle.substring(0, tamCurrent - cutString);
            searchTitleField.setText(sTitle);
            searchTitleField.positionCaret(caret);
        }
    }

    /**
     * Método que delimita a quantidade de caracteres que podem ser inseridos no campo "ISBN" e implementa um filtro
     * para que o usuário somente digite numeros neste campo
     * @since dec 2022
     * @param keyEvent Evento acionado quando uma tecla do keyboard é liberada
     */
    public void ISBNKeyReleased(KeyEvent keyEvent) {

        String sISBN = searchISBNField.getText();

        int caret;
        int cutString;
        int tamCurrent = sISBN.length();
        if(tamCurrent > 13) {
            caret = searchISBNField.getCaretPosition();
            cutString = tamCurrent - 13;
            sISBN = sISBN.substring(0, tamCurrent - cutString);
            searchISBNField.setText(sISBN);
            searchISBNField.positionCaret(caret);
        }

        if(!searchISBNField.getText().matches("\\d+")) {
            caret = searchISBNField.getCaretPosition() - 1;
            sISBN = searchISBNField.getText().replaceAll("\\D", "");
            searchISBNField.setText(sISBN);
            searchISBNField.positionCaret(caret);
        }

    }

    /**
     * Método que busca no Banco de Dados por meio do "Título" e "ISBN" os álbuns que correspondem ao dados inseridos
     * @since dec 2022
     * @param actionEvent
     */
    public void onActionSearch(ActionEvent actionEvent) {

        String sTitle = searchTitleField.getText();
        String sISBN = searchISBNField.getText();

        listViewAlbums.getItems().clear();

        if (currentWindow == Window.AVAILABLE_ALBUMS) {

            if (sTitle == null && sISBN == null) {
                listS = Queries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
            } else if (sTitle == null || sTitle.length() == 0) {
                long lISBN = Long.parseLong(sISBN);
                System.out.println(lISBN);
                listS = Queries.getAlbumsNotFrom((Collector) ScreenManager.getUser(), lISBN);
            } else {
                sTitle = sTitle.toUpperCase();
                listS = Queries.getAlbumsNotFrom((Collector) ScreenManager.getUser(), sTitle);
            }

        } else {

            if (sTitle == null && sISBN == null) {
                listS = Queries.getAllAlbumsFrom((Collector) ScreenManager.getUser());
            } else if (sTitle == null || sTitle.length() == 0) {
                long lISBN = Long.parseLong(sISBN);
                System.out.println(lISBN);
                listS = Queries.getAlbumsFrom((Collector) ScreenManager.getUser(), lISBN);
            } else {
                sTitle = sTitle.toUpperCase();
                listS = Queries.getAlbumsFrom((Collector) ScreenManager.getUser(), sTitle);
            }
        }
        updateListView();
    }

    /**
     * Método que limpa os textfields da screen_collector
     * @since dec 2022
     * @param actionEvent Ação de apagar os campos "Título" e "ISBN"
     */
    public void onActionClear(ActionEvent actionEvent) {
        clearFieldsSearch();
    }

    private void updateListView() {
        for (Album album : listS)
            listViewAlbums.getItems().add(album);
    }

    /**
     * Método utilizado por onActionClear para limpar os campos "Título" e "ISBN"
     * @since dec 20022
     */
    private void clearFieldsSearch() {
        searchTitleField.setText(null);
        searchISBNField.setText(null);
    }

    private void changeWindow() {

        listViewAlbums.getItems().clear();
        clearFieldsSearch();

        if (currentWindow == Window.AVAILABLE_ALBUMS) {
            currentWindow = Window.MY_ALBUMS;
            titleSearchPage.setText(currentWindow.getTitle());
            listViewAlbums.setCellFactory(new VirtualAlbumCellFactory());
            listS = Queries.getAllAlbumsFrom((Collector) ScreenManager.getUser());

        } else {
            currentWindow = Window.AVAILABLE_ALBUMS;
            titleSearchPage.setText(currentWindow.getTitle());
            listViewAlbums.setCellFactory(new AlbumCellFactory());
            listS = Queries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        }

        updateListView();
    }
}
