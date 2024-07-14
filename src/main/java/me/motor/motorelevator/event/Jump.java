package me.motor.motorelevator.event;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.motor.motorelevator.MotorElevator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Jump implements Listener {

    private final MotorElevator plugin;

    public Jump(MotorElevator plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.BONE_BLOCK) {
            for (double y = event.getPlayer().getY(); y<256; y++) {
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
