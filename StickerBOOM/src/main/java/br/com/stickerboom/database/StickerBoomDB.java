package br.com.stickerboom.database;

import br.com.stickerboom.entity.Administrator;
import br.com.stickerboom.entity.Collector;
import oracle.jdbc.OracleDriver;
import oracle.sql.NUMBER;

import java.sql.*;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Representa o banco de dados da aplicação.
 */
public class StickerBoomDB {

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

    /**
     * Instância do banco.
     */
    private static StickerBoomDB instance;

    /**
     * Construtor privado.
     */
    private StickerBoomDB() {
        ResourceBundle db = ResourceBundle.getBundle("br.com.stickerboom.database.db");
        url = db.getString("url");
        user = db.getString("user");
        password = db.getString("password");
    }

    public static void build() {
        if (instance == null)
            instance = new StickerBoomDB();
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static Administrator getAdministrator(String cpf) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ADMINISTRADOR WHERE (CPF = ?)");
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return new Administrator(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static Collector getCollector(String cpf) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM COLECIONADOR WHERE (CPF = ?)");
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return new Collector(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void insertCollector(Collector collector) {

        try (Connection connection = getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO COLECIONADOR VALUES (?, ?, ?, ?)");

            pstmt.setString(1, collector.getCPF());
            pstmt.setString(2, collector.getName());
            pstmt.setString(3, collector.getAddress());
            if (collector.getReputation() != null)
                pstmt.setFloat(4, collector.getReputation());
            else
                pstmt.setFloat(4, 0.0f);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error Code: " + e.getErrorCode());
        }
    }
}
