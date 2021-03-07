package br.com.fimosecraft.punishment.Commands;

import br.com.fimosecraft.punishment.Controller.PunishmentMethods;
import br.com.fimosecraft.punishment.Data.Config.PunishmentFile;
import br.com.fimosecraft.punishment.Data.SQL.Tables.PlayersTable;
import br.com.fimosecraft.punishment.Punishment;
import br.com.fimosecraft.punishment.Utils.Utilities;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PunirCommand implements CommandExecutor {

    final Punishment main;

    public PunirCommand(Punishment main) {
        this.main = main;
        main.getCommand("punir").setExecutor(this);
        main.getCommand("punir").setPermission("Punishment.punir");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        Player player = (Player)sender;

        //Hover Component #HoverEvent(Action, Base) deprecated!
        ComponentBuilder builder = new ComponentBuilder(Utilities.formatText("&8Click para formular a punição!"));
        BaseComponent[] lista = builder.create();
        HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, lista);


        if(args.length == 2) {

            Player playerCheck = Bukkit.getServer().getPlayer(args[0]);
            String nick = playerCheck.getDisplayName();
            String prova = args[1];

            try {

                if(PlayersTable.exists(playerCheck)) {

                    if(args[1].startsWith("https://")) {

                        player.sendMessage(Utilities.formatText(""));
                        player.sendMessage(Utilities.formatText("&aVocê está punindo o Jogador: &2"+nick));
                        player.sendMessage(Utilities.formatText("&aVocê está utilizando a Prova: &2"+prova));
                        player.sendMessage(Utilities.formatText(""));

                        PunishmentMethods.showPunishments(player);

                    } else {
                        player.sendMessage(Utilities.formatText("&c&lERRO:&c Sua prova deve iniciar com 'https://' "));
                    }


                } else {
                    player.sendMessage(Utilities.formatText("&c&lERRO:&c Este player não existe!"));
                }

            } catch (Exception ignore) {

            }

        } else {
            player.sendMessage(Utilities.formatText("&c&lERRO:&C Utilize /punir <nick> <prova>"));
        }

        return false;
    }
}
