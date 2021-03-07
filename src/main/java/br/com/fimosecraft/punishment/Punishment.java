package br.com.fimosecraft.punishment;

import br.com.fimosecraft.punishment.Commands.CheckCommand;
import br.com.fimosecraft.punishment.Commands.PunirCommand;
import br.com.fimosecraft.punishment.Data.Config.PunishmentFile;
import br.com.fimosecraft.punishment.Data.SQL.ConnectionMethod;
import br.com.fimosecraft.punishment.Data.SQL.Tables.PlayersTable;
import br.com.fimosecraft.punishment.Events.PlayerJoin;
import br.com.fimosecraft.punishment.Utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Punishment extends JavaPlugin {

    private static Punishment plugin;
    public static Punishment getInstance(){
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&a[Punishment] iniciado com Sucesso!"));
        plugin = this;
        //Register's
        registerCommands();
        registerEvents();
        registerConfigs();
        registerDataBase();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] desativado com Sucesso!"));

        //DataBase
        ConnectionMethod.closeConnection();

        //Events
        HandlerList.unregisterAll(this);

    }

    public void registerCommands() {
        new PunirCommand(this);
        new CheckCommand(this);
    }

    public void registerEvents() {
        new PlayerJoin(this);
    }

    public void registerConfigs() {
        saveDefaultConfig();
        File fileConfig = new File(getDataFolder(), "config.yml");
        if(!fileConfig.exists()) {
            Bukkit.getConsoleSender().sendMessage(Utilities.formatText("&c[Punishment] Erro ao encontrar (config.yml)"));
        }
        PunishmentFile.setupConfigFile(this);
    }

    public void registerDataBase() {
        ConnectionMethod.openConnection();
        //PunishmentsTable.createTable();
        PlayersTable.createTable();
    }

}
