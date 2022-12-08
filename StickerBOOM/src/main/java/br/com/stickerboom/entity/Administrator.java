package br.com.stickerboom.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User {

    public Administrator(ResultSet resultSet) throws SQLException {
        setCPF(resultSet.getString(1));
        setName(resultSet.getString(2));
        setAddress(resultSet.getString(3));
    }
}
