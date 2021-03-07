package br.com.fimosecraft.punishment.Data.SQL.Tables;

import br.com.fimosecraft.punishment.Data.SQL.ConnectionMethod;
import br.com.fimosecraft.punishment.Utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayersTable {
    public static void createTable() {
        try {
            //Campos: UUID, Nick, create_at,
            PreparedStatement statement = ConnectionMethod.Query("CREATE TABLE IF NOT EXISTS Players(UUID VARCHAR(36), NICK VARCHAR(16), CREATED DATETIME);");
            assert statement != null;
            statement.executeUpdate();
            statement.close();

            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&a[Punishment] Tabela (Players) criada com Sucesso!"));

        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao criar Tabela (Players)!"));
            exception.printStackTrace();
        }
    }

    public static void insert(Player player) {

        UUID id = player.getUniqueId();

        try {

            PreparedStatement statement = ConnectionMethod.Query("INSERT INTO Players(UUID, NICK, CREATED) VALUES (?,?,now())");
            statement.setString(1, id.toString());
            statement.setString(2, player.getDisplayName());
            assert statement != null;
            statement.executeUpdate();
            statement.close();

        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao Inserir Jogdor em (Players)!"));
            exception.printStackTrace();
        }

    }

    public static boolean exists(Player player) {

        UUID id = player.getUniqueId();

        try {
            PreparedStatement statement = ConnectionMethod.Query("SELECT UUID FROM Players WHERE UUID = ?;");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public static String getCreated(Player player) {
        String date = "null";

        UUID id = player.getUniqueId();

        try {
            PreparedStatement statement = ConnectionMethod.Query("SELECT CREATED FROM Players WHERE UUID = ?;");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                date = resultSet.getString("CREATED");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return date;
    }

}
