package br.com.fimosecraft.punishment.Data.SQL.Tables;

import br.com.fimosecraft.punishment.Data.SQL.ConnectionMethod;
import br.com.fimosecraft.punishment.Utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PunishmentsTable {

    public static void createTable() {
        try {
            //Campos: UUID, NICK, TYPE, BY, REASON, EXPIRE, EVIDENCE; Types: MUTE BAN
            //Comando /punir <nick> <reason> <evidence> <expire> / Ideia
            //Comando /punir <nick> <evidence> / Final
            //Comando <List of Reasons> /
            //Comando <Click>
            //Comando Confirmar Punição
            PreparedStatement statement = ConnectionMethod.Query("CREATE TABLE IF NOT EXISTS Punishments(UUID VARCHAR(36), NICK VARCHAR(16), TYPE VARCHAR(4), BY VARCHAR(16), REASON VARCHAR(50), EXPIRE DATETIME, EVIDENCE VARCHAR(30));");
            assert statement != null;
            statement.executeUpdate();
            statement.close();

            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&a[Punishment] Tabela (Punishments) criada com Sucesso!"));

        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao criar Tabela (Punishments)!"));
            exception.printStackTrace();
        }
    }

    //Métodos hasMuted hasBanned addPunishment

    public static void addPunishment(UUID id, String nick, String type, String by, String reason, String expire, String evidence) {
        try {

            PreparedStatement statement = ConnectionMethod.Query("INSERT INTO Punishments(UUID, NICK, TYPE, BY, REASON, EXPIRE, EVIDENCE) VALUES (?,?,?,?,?,?);");
            statement.setString(1, id.toString());
            statement.setString(2, nick);

            assert statement != null;
            statement.executeUpdate();

        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao inserir Jogador em (Punishments)!"));
            exception.printStackTrace();
        }




    }

}
