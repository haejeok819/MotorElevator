package me.motor.motorelevator.command;

import me.motor.motorelevator.MotorElevator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    private final MotorElevator plugin;

    public MainCommand(MotorElevator plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("§c/motorelevator reload");
            return false;
        }
        if (args[0].equals("reload")) {
            if (sender.hasPermission("motor.elevator.reload")) {
                plugin.reloadMessagesConfig();
                sender.sendMessage("§bMotorElevator Reloaded");
                return true;
            }
            sender.sendMessage("§cYou don't have permission to use that command :(");
            return false;
        }
        return false;

    }
}
