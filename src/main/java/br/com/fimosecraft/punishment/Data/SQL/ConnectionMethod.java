package br.com.fimosecraft.punishment.Data.SQL;

import br.com.fimosecraft.punishment.Punishment;
import br.com.fimosecraft.punishment.Utils.Utilities;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionMethod {

    static Connection connection;
    final Punishment main;
    public static Punishment instance = Punishment.getInstance();

    public ConnectionMethod(Punishment main) {
        this.main = main;
    }

    public static void openConnection() {
        try {

            String user = instance.getConfig().getString("MySQL.user"); //"root";
            String password = instance.getConfig().getString("MySQL.password"); // "";
            String host = instance.getConfig().getString("MySQL.host"); //"localhost";
            int port = instance.getConfig().getInt("MySQL.port"); //"3306";
            String database = instance.getConfig().getString("MySQL.database"); //"testes";
            String type = "jdbc:mysql://";
            String url = type+host+":"+port+"/"+database+"?autoReconnect=true";

            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&a[Punishment] Conectado com Sucesso!"));

            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro de Conexão!"));
            exception.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {

            connection.close();

        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao Fechar Conexão!"));
            exception.printStackTrace();
        }
    }

    public static PreparedStatement Query(String sql) {
        try {
            assert connection != null;
            return connection.prepareStatement(sql);
        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao Iniciar Query!"));
            exception.printStackTrace();
        }
        return null;
    }

}
