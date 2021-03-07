package br.com.fimosecraft.punishment.Controller;

import br.com.fimosecraft.punishment.Data.Config.PunishmentFile;
import org.bukkit.entity.Player;

import java.util.List;

public class PunishmentMethods {

    public static List<String> punishments;


    public static List<String> getPunishments() {
        return punishments;
    }

    public static void setPunishments(List<String> punishments) {
        PunishmentMethods.punishments = punishments;
    }

    public static void createComponent() {

    }

    public static void showPunishments(Player player) {

        setPunishments(punishments = PunishmentFile.getConfigFile().getStringList("punishments"));

        for(int i = 0; i < punishments.size(); i++) {
            player.sendMessage("Linha da vez: "+punishments.get(i));
        }

    }


}
