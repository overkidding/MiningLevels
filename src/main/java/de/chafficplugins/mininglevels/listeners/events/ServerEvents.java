package de.chafficplugins.mininglevels.listeners.events;

import de.chafficplugins.mininglevels.api.MiningPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

import static de.chafficplugins.mininglevels.utils.SenderUtils.sendDebug;

public class ServerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        if(MiningPlayer.notExists(player.getUniqueId())) {
            new MiningPlayer(player.getUniqueId(), player.getName(), 0, 0);
            MiningPlayer.save();
            sendDebug(player, "PlayerJoinEvent: Player created.");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException {
        MiningPlayer.save();
    }
}
