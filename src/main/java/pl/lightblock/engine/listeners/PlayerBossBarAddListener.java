package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.managers.Scoreboard;
import pl.lightblock.engine.utils.Bar;

public class PlayerBossBarAddListener implements Listener {

    Bar bar;
    public PlayerBossBarAddListener(Bar bar)
    {
        this.bar = bar;
    }

    @SuppressWarnings("static-access")
    @EventHandler
    public void JoinListener(PlayerJoinEvent e) {
        if(!bar.getBar().getPlayers().contains(e.getPlayer()))
        {
            bar.addPlayer(e.getPlayer());
        }
    }
}