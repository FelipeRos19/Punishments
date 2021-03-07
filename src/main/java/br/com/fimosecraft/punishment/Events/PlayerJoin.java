package br.com.fimosecraft.punishment.Events;

import br.com.fimosecraft.punishment.Data.SQL.Tables.PlayersTable;
import br.com.fimosecraft.punishment.Punishment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    final Punishment main;

    public PlayerJoin(Punishment main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!PlayersTable.exists(player)) {
            PlayersTable.insert(player);
        }

    }
}
