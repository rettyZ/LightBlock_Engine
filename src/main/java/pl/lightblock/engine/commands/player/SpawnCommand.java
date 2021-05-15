package pl.lightblock.engine.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import pl.lightblock.engine.Main;

import java.util.HashMap;

public class SpawnCommand implements CommandExecutor, Listener {
    private Main plugin;
    BukkitTask task;
    public HashMap<String, BukkitTask> taskmap = new HashMap<>();
    public SpawnCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(" ");
            commandSender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/home)");
            commandSender.sendMessage(" ");
        } else {
            int delay = 10;
            if (p.hasPermission("lightblock.spawn.vip"))
            {
                delay = 5;
            }
            if(p.hasPermission("lightblock.spawn.admin") || p.isOp())
            {
                delay = 0;
            }
            p.sendMessage("§8» §7Trwa teleportacja... Nie ruszaj się przez " + delay + " sekund.");
            task = Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    double x = plugin.getConfig().getDouble("spawn.x");
                    double y = plugin.getConfig().getDouble("spawn.y");
                    double z = plugin.getConfig().getDouble("spawn.z");
                    World w = plugin.getServer().getWorld(plugin.getConfig().getString("spawn.world", "world"));
                    Location loc = new Location(w, x, y, z);
                    p.teleport(loc);
                    p.sendMessage("§8» §7Przeniesiono na spawn!");
                    taskmap.remove(p.getName());
                }
            }, 20*delay);
            taskmap.put(p.getName(), task);
        }
        return false;
    }
}
