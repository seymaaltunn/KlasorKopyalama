package repository;

import database.DatabaseConnection;
import model.Evrak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EvrakRepository {

    public List<Evrak> tumEvraklariGetir() {

        List<Evrak> evraklar = new ArrayList<>();

        String sql = "SELECT * FROM Evraklar";

        try (
                Connection connection =
                        DatabaseConnection
                                .getInstance()
                                .getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Evrak evrak = new Evrak();

                evrak.setId(resultSet.getInt("Id"));
                evrak.setEvrakId(resultSet.getInt("EvrakId"));
                evrak.setKlasorAdi(resultSet.getString("KlasorAdi"));

                evraklar.add(evrak);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return evraklar;
    }
}