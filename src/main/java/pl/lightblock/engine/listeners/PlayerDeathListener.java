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
import org.bukkit.scheduler.BukkitRunnable;
import pl.lightblock.engine.Main;

public class PlayerDeathListener implements Listener {
    Main plugin;
    public PlayerDeathListener(Main plugin)
    {
        this.plugin = plugin;
    }
    @EventHandler
    public void OnDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        e.getEntity().setHealth(20);
        e.getEntity().setGameMode(GameMode.SPECTATOR);
        e.getEntity().sendTitle("§cNie żyjesz!", e.getDeathMessage(), 0, 20*5, 20);
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                double x = plugin.getConfig().getDouble("spawn.x");
                double y = plugin.getConfig().getDouble("spawn.y");
                double z = plugin.getConfig().getDouble("spawn.z");
                World w = plugin.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
                Location loc = new Location(w, x, y, z);
                p.spigot().respawn();
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(loc);
            }
        }, 20*5);
    }
}
