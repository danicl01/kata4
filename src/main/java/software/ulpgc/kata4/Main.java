package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:Sqlite:Pokemon2.db")) {
            PokemonLoader loader = SqlitePokemonLoader(connection);
            //loader.load()

        }
    }
}