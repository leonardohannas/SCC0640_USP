package br.com.stickerboom.view;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.database.CommonQueries;
import br.com.stickerboom.entity.Collector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenCollectorController implements Initializable {

    @FXML
    private ListView<Album> listViewAlbumsSystem;
//
//    @FXML
//    private ListView<Album> listViewAlbumsCollector;
//
//    @FXML
//    private Text collectorNameText;
//
//    @FXML
//    private Label addressCollectorLabel;
//
//    @FXML
//    private Label reputationCollectorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        collectorNameText.setText(ScreenManager.getUser().getName());
//        addressCollectorLabel.setText(ScreenManager.getUser().getAddress());
//        reputationCollectorLabel.setText("Reputação: " + Float.toString(((Collector) ScreenManager.getUser()).getReputation()));
//
        listViewAlbumsSystem.setCellFactory(new AlbumCellFactory());
//        listViewAlbumsCollector.setCellFactory(new VirtualAlbumCellFactory());
//
//        List<Album> listS = CommonQueries.getAllAlbumsExcept((Collector) ScreenManager.getUser());
        List<Album> listS = CommonQueries.getAllAlbums();
        for (Album album : listS)
            listViewAlbumsSystem.getItems().add(album);
//
//        List<Album> listC = CommonQueries.getAllAlbumsFrom((Collector) ScreenManager.getUser());
//        for (Album album : listC)
//            listViewAlbumsCollector.getItems().add(album);
//
    }
}
