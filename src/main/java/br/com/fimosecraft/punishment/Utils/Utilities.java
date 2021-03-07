package br.com.fimosecraft.punishment.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Utilities {

    //Faz Replace de "&" para "§".
    public static String formatText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    //Remover a Cor.
    public static String clearText(String text) {
        return ChatColor.stripColor(text);
    }

    //Pega Offline Players
    public static OfflinePlayer getOfflinePlayer (String name) {
        for (OfflinePlayer player: Bukkit.getOfflinePlayers ()) {
            if (player.getName (). equals (name)) return player;
        }
        return null;
    }

}
