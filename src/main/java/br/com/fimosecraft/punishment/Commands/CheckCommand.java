package br.com.fimosecraft.punishment.Commands;

import br.com.fimosecraft.punishment.Data.SQL.Tables.PlayersTable;
import br.com.fimosecraft.punishment.Punishment;
import br.com.fimosecraft.punishment.Utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CheckCommand implements CommandExecutor {

    final Punishment main;

    public CheckCommand(Punishment main) {
        this.main = main;
        main.getCommand("check").setExecutor(this);
        main.getCommand("check").setPermission("Punishment.check");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        Player player = (Player)sender;

        /*
        String nick = args[0];
        Player playerCheck = Bukkit.getServer().getPlayer(nick);
        String ip = playerCheck.getAddress().toString();
        UUID id = playerCheck.getUniqueId();
        */

        if(args.length == 1) {

            Player playerCheck = Bukkit.getServer().getPlayer(args[0]);

            try {
                if(PlayersTable.exists(playerCheck)) {

                    String nick = args[0];
                    String ip = playerCheck.getAddress().toString();
                    UUID id = playerCheck.getUniqueId();
                    String created = PlayersTable.getCreated(playerCheck);

                    player.sendMessage(Utilities.formatText("&aInformações sobre: &2" + nick));
                    player.sendMessage("");
                    player.sendMessage(Utilities.formatText("&a Informações Básicas"));
                    player.sendMessage(Utilities.formatText("&a  ID: &7" + id.toString()));
                    player.sendMessage(Utilities.formatText("&a  Data de Registro: &7" + created));
                    player.sendMessage(Utilities.formatText("&a  Online: &2Sim"));
                    player.sendMessage(Utilities.formatText("&a  Punido: &cSim"));
                    player.sendMessage("");
                    player.sendMessage(Utilities.formatText("&a Informações Avançadas"));
                    player.sendMessage(Utilities.formatText("&a  IP: &7" + ip.replace("/", "")));
                    player.sendMessage("");

                }

            } catch (Exception exception) {
                player.sendMessage(Utilities.formatText("&c&lERRO: &cEste player não existe!"));
            }


        } else {
            player.sendMessage(Utilities.formatText("&c&lERRO: &cUtilize /check <nick>"));
        }




        return false;
    }
}
