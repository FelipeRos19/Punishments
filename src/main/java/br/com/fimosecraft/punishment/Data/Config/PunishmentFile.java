package br.com.fimosecraft.punishment.Data.Config;

import br.com.fimosecraft.punishment.Punishment;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PunishmentFile {

    public static Punishment plugin;
    private static File file;
    private static FileConfiguration fileConfiguration;

    public PunishmentFile(Punishment main) {
        PunishmentFile.plugin = main;
    }

    public static void setupConfigFile(final Punishment main) {
        PunishmentFile.plugin = main;

        if (!PunishmentFile.plugin.getDataFolder().exists()) {
            PunishmentFile.plugin.getDataFolder().mkdir();
        }

        PunishmentFile.file = new File(PunishmentFile.plugin.getDataFolder(), "punishments.yml");

        if (!PunishmentFile.file.exists()) {
            try {
                PunishmentFile.plugin.saveResource("punishments.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("Erro ao criar punishments.yml");
                localException1.printStackTrace();
            }
        }
        PunishmentFile.fileConfiguration = YamlConfiguration.loadConfiguration(PunishmentFile.file);
    }

    public static void reload() {
        PunishmentFile.fileConfiguration = YamlConfiguration.loadConfiguration(PunishmentFile.file);
    }

    public static FileConfiguration getConfigFile() {
        return PunishmentFile.fileConfiguration;
    }

    public static void save() {
        try {
            PunishmentFile.fileConfiguration.save(PunishmentFile.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
