package br.com.stickerboom.view;

import br.com.stickerboom.album.Album;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AlbumCellFactory implements Callback<ListView<Album>, ListCell<Album>> {

    @Override
    public ListCell<Album> call(ListView<Album> albumListView) {
        return new AlbumCell();
    }
}
