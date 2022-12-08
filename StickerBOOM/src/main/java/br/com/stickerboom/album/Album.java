package br.com.stickerboom.album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

public class Album {

    private long ISBN;
    private String title;
    private int stickerNumber;
    private String administrator;
    private Date date;

    public Album(ResultSet resultSet) throws SQLException {
        this.ISBN = resultSet.getLong(1);
        this.title = resultSet.getString(2);
        this.stickerNumber = resultSet.getInt(3);
        this.administrator = resultSet.getString(4);
        this.date = resultSet.getDate(5);
    }

    public long getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getStickerNumber() {
        return stickerNumber;
    }

    @Override
    public String toString() {
        return "Album{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", stickerNumber=" + stickerNumber +
                ", administrator='" + administrator + '\'' +
                ", date=" + date +
                '}';
    }
}
