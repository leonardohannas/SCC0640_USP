package br.com.stickerboom.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonQueries {

//        public static getUser(String CPF) {
//
//            try (Connection con = DBConnection.getConnection()) {
//
//                PreparedStatement pstmt = con.prepareStatement(
//                        "SELECT * FROM CARGO WHERE (CPF = ?)");
//                pstmt.setString(1, CPF);
//                ResultSet resultSet = pstmt.executeQuery();
//
//                if (resultSet.next())
//                    System.out.println(resultSet.getString(1));
//
//                return resultSet;
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
}
