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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    public void onActionClear(ActionEvent actionEvent) {
        clearFieldsSearch();
    }

    private void updateListView() {
        for (Album album : listS)
            listViewAlbums.getItems().add(album);
    }

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
