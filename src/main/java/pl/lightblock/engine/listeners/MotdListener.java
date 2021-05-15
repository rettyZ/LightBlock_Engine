package pl.lightblock.engine.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import pl.lightblock.engine.Main;

public class MotdListener implements Listener {
    Main plugin;
    public MotdListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void MotdListener (final ServerListPingEvent e) {
        String motdline1 = plugin.getConfig().getString("motd-line1");
        String motdline2 = plugin.getConfig().getString("motd-line2");

        e.setMotd(ChatColor.translateAlternateColorCodes('&', motdline1 + "\n" + motdline2));
        e.setMaxPlayers(25565);
    }
}