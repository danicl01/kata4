package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlitePokemonLoader implements PokemonLoader {
    private final Connection connection
            ;
    private final static String SqlQuery = "" +
            "SELECT DISTINCT POKEMON.name, POKEMON.type1, POKEMON.type2 " +
            "FROM POKEMON " +
            "JOIN MOVES ON lower(MOVES.moveType) = POKEMON.type1";

    public SqlitePokemonLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pokemon> loadPokemon() {
        try {
            return loadPokemon(resultSet(SqlQuery));
        } catch(SQLException e) {
            return Collections.emptyList();
        }
    }

    private ResultSet resultSet(String sqlQuery) throws SQLException {
        return connection.createStatement().executeQuery(sqlQuery);
    }

    public List<Pokemon> loadPokemon(ResultSet resultSet) throws SQLException {
        List<Pokemon> pokemonList = new ArrayList<>();
        while(resultSet.next()) pokemonList.add(newPokemon(resultSet));
        return pokemonList;
    }

    private Pokemon newPokemon(ResultSet resultSet) throws SQLException {
        return new Pokemon(
                resultSet.getString("name"),
                resultSet.getString("type1"),
                resultSet.getString("type2")
        );
    }
}
