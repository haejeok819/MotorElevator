package me.motor.motorelevator.event;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.motor.motorelevator.MotorElevator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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
        double playerY = player.getLocation().getY();
        double playerX = player.getLocation().getX();
        double playerZ = player.getLocation().getZ();
        float playerYaw = player.getLocation().getYaw();
        float playerPitch = player.getLocation().getPitch();
        World playerWorld = player.getWorld();

        if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.BONE_BLOCK || event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() == Material.BONE_BLOCK) {
            for (double y = playerY; y<256; y++) {
                Location checkLoc = new Location(playerWorld, playerX, y, playerZ, playerYaw, playerPitch);
                if (checkLoc.getBlock().getType() == Material.BONE_BLOCK) {
                    FileConfiguration messagesConfig = plugin.getMessagesConfig();
                    Location tpLoc = new Location(playerWorld, playerX, y+1, playerZ, playerYaw, playerPitch);
                    player.teleport(tpLoc);

                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                    player.sendTitle(messagesConfig.getString("messages.title"), messagesConfig.getString("messages.subtitle"), messagesConfig.getInt("messages.fadein"), messagesConfig.getInt("messages.time"), messagesConfig.getInt("messages.fadeout"));
                    break;
                }

            }

        }
    }

}
