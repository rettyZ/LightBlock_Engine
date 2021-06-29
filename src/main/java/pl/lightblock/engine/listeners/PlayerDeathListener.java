package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import pl.lightblock.engine.Main;

import java.util.HashMap;

public class PlayerDeathListener implements Listener {
    Main plugin;
    public PlayerDeathListener(Main plugin)
    {
        this.plugin = plugin;
    }
    @EventHandler
    public void OnDeath(PlayerDeathEvent e) {
        if(plugin.getConfig().getBoolean("showDeathScreen"))
        {
            String deathScreenTitle = "§cNIE ŻYJESZ!";
            String deathScreenSubtitle = e.getDeathMessage();
            e.getEntity().sendTitle(deathScreenTitle, deathScreenSubtitle, 0, 20*5, 20);
        }
    }
}
