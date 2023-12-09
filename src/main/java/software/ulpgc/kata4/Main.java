package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:Sqlite:Pokemon2.db")) {
            PokemonLoader loader = new SqlitePokemonLoader(connection);
            List<Pokemon> pokemonNameList = loader.loadPokemon();
            for(Pokemon pokemon: pokemonNameList) {
                System.out.println(pokemon.name());
            }
        }
    }
}