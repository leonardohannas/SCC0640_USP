package br.com.stickerboom.database;

import br.com.stickerboom.album.Album;
import br.com.stickerboom.entity.Administrator;
import br.com.stickerboom.entity.Collector;
import br.com.stickerboom.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CommonQueries {

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
}
