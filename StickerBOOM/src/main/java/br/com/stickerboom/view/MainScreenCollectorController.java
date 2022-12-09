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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenCollectorController implements Initializable {

    @FXML
    private ListView<Album> listViewAlbumsSystem;
    private List<Album> listS;

    @FXML
    private TextField searchTitleField;

    @FXML
    private TextField searchISBNField;

    @FXML
    private Label collectorNameLabel;

    @FXML
    private Label reputationCollectorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectorNameLabel.setText(ScreenManager.getUser().getName());
//        addressCollectorLabel.setText(ScreenManager.getUser().getAddress());
        reputationCollectorLabel.setText("Reputação: " + Float.toString(((Collector) ScreenManager.getUser()).getReputation()));
//
        listViewAlbumsSystem.setCellFactory(new AlbumCellFactory());
//        listViewAlbumsCollector.setCellFactory(new VirtualAlbumCellFactory());
//
//        List<Album> listS = CommonQueries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        listS = Queries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        for (Album album : listS)
            listViewAlbumsSystem.getItems().add(album);
//
//        List<Album> listC = CommonQueries.getAllAlbumsFrom((Collector) ScreenManager.getUser());
//        for (Album album : listC)
//            listViewAlbumsCollector.getItems().add(album);
//
    }

    public void onActionSearch(ActionEvent actionEvent) {

        String sTitle = searchTitleField.getText();
        String sISBN = searchISBNField.getText();

        listViewAlbumsSystem.getItems().clear();

        if (sTitle == null && sISBN == null) {
            listS = Queries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        } else if (sTitle == null || sTitle.length() == 0) {
            long lISBN = Long.parseLong(sISBN);
            System.out.println(lISBN);
            listS = Queries.getAlbumsNotFromCollectorWhere(lISBN, (Collector) ScreenManager.getUser());
        } else {
            sTitle = sTitle.toUpperCase();
            listS = Queries.getAlbumsNotFromCollectorWhere(sTitle, (Collector) ScreenManager.getUser());
        }

        for (Album album : listS)
            listViewAlbumsSystem.getItems().add(album);
    }

    public void onActionClear(ActionEvent actionEvent) {
        searchTitleField.setText(null);
        searchISBNField.setText(null);
    }
}
