package br.com.stickerboom.database;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {

    /**
     * URL de conexão com o banco de dados.
     */
    private static String url;

    /**
     * Usuário do banco de dados.
     */
    private static String user;

    /**
     * Senha do usuário.
     */
    private static String password;

    private static DBConnection instance;

    private  DBConnection () {
        ResourceBundle db = ResourceBundle.getBundle("br.com.stickerboom.database.db");
        url = db.getString("url");
        user = db.getString("user");
        password = db.getString("password");
    }

    public static void build() {
        if (instance == null)
            instance = new DBConnection();
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        return DriverManager.getConnection(url, user, password);
    }
}
