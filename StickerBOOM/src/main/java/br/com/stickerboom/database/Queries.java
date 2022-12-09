package br.com.stickerboom.database;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.entity.Administrator;
import br.com.stickerboom.entity.Collector;
import br.com.stickerboom.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Queries {

        public static int insertCollector(String cpf, String name, String address) {

            Connection con = null;
            int result = 0;

            try  {

                con = DBConnection.getConnection();
                con.setAutoCommit(false);

                PreparedStatement pStmt = con.prepareStatement(
                        "INSERT INTO CARGO VALUES (?, 'USER')");

                pStmt.setString(1, cpf);

                result = pStmt.executeUpdate();

                if (result != 1) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return result;
                }

                pStmt = con.prepareStatement(
                        "INSERT INTO COLECIONADOR VALUES " +
                                "(?, ?, ?, DEFAULT)");
                
                pStmt.setString(1, cpf);
                pStmt.setString(2, name);
                pStmt.setString(3, address);

                result = pStmt.executeUpdate();

                if (result != 1) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return result;
                }

                con.commit();

            } catch (SQLException e) {

                try {
                    if (con != null)
                        con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            } finally {

                try {
                    if (con != null)
                        con.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            
            return result;
        }

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

        public static List<Album> getAlbumsNotFrom(Collector collector, String title, Long ISBN) {

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


        public static List<Album> getAlbumsNotFrom(Collector collector, String title) {

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

    public static List<Album> getAlbumsNotFrom(Collector collector, long ISBN) {

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


    public static List<Album> getAlbumsFrom(Collector collector, String title, long ISBN) {

            try (Connection con = DBConnection.getConnection()) {

                PreparedStatement pStmt = con.prepareStatement(
                        "SELECT * FROM ALBUM " +
                                "WHERE ISBN IN " +
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

    public static List<Album> getAlbumsFrom(Collector collector, String title) {

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement pStmt = con.prepareStatement(
                    "SELECT * FROM ALBUM " +
                            "WHERE ISBN IN " +
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

    public static List<Album> getAlbumsFrom(Collector collector, long ISBN) {

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement pStmt = con.prepareStatement(
                    "SELECT * FROM ALBUM " +
                            "WHERE ISBN IN " +
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
