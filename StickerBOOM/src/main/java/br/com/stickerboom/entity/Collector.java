package br.com.stickerboom.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Collector extends User {

    private Float reputation;

    public Collector(String CPF, String name, String address) {
        setCPF(CPF);
        setName(name);
        setAddress(address
        );
    }

    public Collector(ResultSet resultSet) throws SQLException {
        setCPF(resultSet.getString(1));
        setName(resultSet.getString(2));
        setAddress(resultSet.getString(3));
        this.reputation = resultSet.getFloat(4);
    }

    public Float getReputation() {
        return reputation;
    }

    public void setReputation(Float reputation) {
        this.reputation = reputation;
    }


    @Override
    public String toString() {
        return "Collector{" +
                "reputation=" + reputation +
                "} " + super.toString();
    }

}
