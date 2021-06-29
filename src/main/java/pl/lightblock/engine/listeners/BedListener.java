package pl.lightblock.engine.listeners;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {
    @EventHandler
    public void onBed(PlayerBedEnterEvent e){
        e.setCancelled(true);
        e.getPlayer().sendMessage("§cNie możesz używać łóżka!");
    }
}