package br.com.stickerboom.view;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.database.CommonQueries;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenCollectorController implements Initializable {

    @FXML
    ListView<Album> listViewAlbumsSystem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewAlbumsSystem.setCellFactory(new AlbumCellFactory());
        List<Album> list = CommonQueries.getAllAlbums();
        for (Album album : list) {
            System.out.println(album);
            listViewAlbumsSystem.getItems().add(album);
        }
    }
}
