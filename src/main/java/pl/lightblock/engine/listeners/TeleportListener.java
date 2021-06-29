package pl.lightblock.engine.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {
    @EventHandler
    public void OnTeleport(PlayerTeleportEvent e)
    {
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) { e.setCancelled(true); }
    }
}