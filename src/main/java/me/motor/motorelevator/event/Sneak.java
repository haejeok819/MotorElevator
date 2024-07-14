package me.motor.motorelevator.event;

import me.motor.motorelevator.MotorElevator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Sneak implements Listener {
    private final MotorElevator plugin;

    public Sneak(MotorElevator plugin) {

        this.plugin = plugin;
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            return;
        }
        if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.BONE_BLOCK) {
            for (double y = event.getPlayer().getY()-2; y>-70; y--) {
                Location checkLoc = new Location(player.getWorld(), player.getX(), y, player.getZ(), player.getYaw(), player.getPitch());
                if (checkLoc.getBlock().getType() == Material.BONE_BLOCK) {
                    FileConfiguration messagesConfig = plugin.getMessagesConfig();
                    player.teleport(checkLoc.add(0, 1, 0));
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                    player.sendTitle(messagesConfig.getString("messages.title"), messagesConfig.getString("messages.subtitle"), messagesConfig.getInt("messages.fadein"), messagesConfig.getInt("messages.time"), messagesConfig.getInt("messages.fadeout"));

                    break;
                }

            }

        }
    }
}
