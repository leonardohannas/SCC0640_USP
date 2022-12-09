package br.com.stickerboom.database;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.entity.Administrator;
import br.com.stickerboom.entity.Collector;
import br.com.stickerboom.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Queries {

        public static User getUser(String CPF) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT CARGO FROM CARGO WHERE CPF = ?");
                pStmt.setString(1, CPF);
                ResultSet resultSet = pStmt.executeQuery();

                if (resultSet.next()) {
                    String cargo = resultSet.getString(1).trim();
                    switch (cargo) {
                        case "USER":
                            pStmt = con.prepareStatement(
                                    "SELECT * FROM COLECIONADOR WHERE CPF = ?");
                            pStmt.setString(1, CPF);
                            resultSet = pStmt.executeQuery();

                            if (resultSet.next())
                                return new Collector(resultSet);
                            else return null;

                        case "ADM":
                            pStmt = con.prepareStatement(
                                    "SELECT * FROM ADMINISTRADOR WHERE CPF = ?");
                            pStmt.setString(1, CPF);
                            resultSet = pStmt.executeQuery();

                            if (resultSet.next())
                                return new Administrator(resultSet);
                            else return null;

                        default:
                            return null;
                    }
                } else return null;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Album> getAllAlbums() {

            try (Connection con = DBConnection.getConnection()) {

                Statement stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(
                        "SELECT * FROM ALBUM");

                List<Album> albumList = new LinkedList<>();

                while (resultSet.next())
                    albumList.add(new Album(resultSet));

                return albumList;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Album> getAllAlbumsExcept(Collector collector) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT * FROM ALBUM " +
                        "WHERE ISBN NOT IN " +
                        "(SELECT AV.ALBUM FROM ALBUM_VIRTUAL AV " +
                        "WHERE ? = AV.COLECIONADOR)");

                pStmt.setString(1, collector.getCPF());
                ResultSet resultSet = pStmt.executeQuery();
                List<Album> albumList = new LinkedList<>();

                while (resultSet.next())
                    albumList.add(new Album(resultSet));

                return albumList;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Album> getAllAlbumsFrom(Collector collector) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT * FROM ALBUM " +
                                "WHERE ISBN IN " +
                                "(SELECT AV.ALBUM FROM ALBUM_VIRTUAL AV " +
                                "WHERE ? = AV.COLECIONADOR)"
                );

                pStmt.setString(1, collector.getCPF());
                ResultSet resultSet = pStmt.executeQuery();

                List<Album> albumList = new LinkedList<>();

                while (resultSet.next())
                    albumList.add(new Album(resultSet));

                return albumList;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        public static Integer getStickerNumberInVirtualAlbum(long ISBN, Collector collector) {
            
            Integer retVal = null;

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT COUNT(*) FROM ALBUM_VIRTUAL_FIGURINHA " +
                                "WHERE ALBUM_V = ? AND COLECIONADOR = ?"
                );

                pStmt.setLong(1, ISBN);
                pStmt.setString(2, collector.getCPF());

                ResultSet resultSet = pStmt.executeQuery();
                if (resultSet.next())
                    retVal = resultSet.getInt(1);

                return retVal;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Album> getAlbumsNotFromCollectorWhere(String title, Long ISBN, Collector collector) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT * FROM ALBUM " +
                                "WHERE ISBN NOT IN " +
                                "(SELECT AV.ALBUM FROM ALBUM_VIRTUAL AV " +
                                    "WHERE ? = AV.COLECIONADOR) " +
                                "AND UPPER(TITULO) LIKE UPPER((CONCAT(CONCAT('%', ?), '%'))) " +
                                "AND TO_CHAR(ISBN) LIKE CONCAT(TO_CHAR(?), '%')");

                pStmt.setString(1, collector.getCPF());
                pStmt.setString(2, title);
                pStmt.setLong(3, ISBN);

                ResultSet resultSet = pStmt.executeQuery();

                List<Album> retVal = new LinkedList<>();
                while (resultSet.next())
                    retVal.add(new Album(resultSet));

                return retVal;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        public static List<Album> getAlbumsNotFromCollectorWhere(String title, Collector collector) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT * FROM ALBUM " +
                                "WHERE ISBN NOT IN " +
                                "(SELECT AV.ALBUM FROM ALBUM_VIRTUAL AV " +
                                "WHERE ? = AV.COLECIONADOR) " +
                                "AND UPPER(TITULO) LIKE UPPER((CONCAT(CONCAT('%', ?), '%')))");

                pStmt.setString(1, collector.getCPF());
                pStmt.setString(2, title);

                ResultSet resultSet = pStmt.executeQuery();

                List<Album> retVal = new LinkedList<>();
                while (resultSet.next())
                    retVal.add(new Album(resultSet));

                return retVal;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public static List<Album> getAlbumsNotFromCollectorWhere(long ISBN, Collector collector) {

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement pStmt = con.prepareStatement(
                    "SELECT * FROM ALBUM " +
                            "WHERE ISBN NOT IN " +
                            "(SELECT AV.ALBUM FROM ALBUM_VIRTUAL AV " +
                            "WHERE ? = AV.COLECIONADOR) " +
                            "AND TO_CHAR(ISBN) LIKE CONCAT(TO_CHAR(?), '%')");

            pStmt.setString(1, collector.getCPF());
            pStmt.setLong(2, ISBN);

            ResultSet resultSet = pStmt.executeQuery();

            List<Album> retVal = new LinkedList<>();
            while (resultSet.next())
                retVal.add(new Album(resultSet));

            return retVal;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
