package me.motor.motorelevator;


import me.motor.motorelevator.command.MainCommand;
import me.motor.motorelevator.event.Jump;
import me.motor.motorelevator.event.Sneak;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MotorElevator extends JavaPlugin {

    @Override
    public void onEnable() {
        createMessagesConfig();
        getCommand("motorelevator").setExecutor(new MainCommand(this));
        getServer().getPluginManager().registerEvents(new Jump(this), this);
        getServer().getPluginManager().registerEvents(new Sneak(this), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private FileConfiguration messagesConfig;
    private void createMessagesConfig() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public void reloadMessagesConfig() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (messagesFile.exists()) {
            messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        }
    }

}
