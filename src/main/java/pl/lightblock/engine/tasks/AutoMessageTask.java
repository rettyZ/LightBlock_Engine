package pl.lightblock.engine.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.lightblock.engine.Main;

import java.util.List;

public class AutoMessageTask {

    static Main plugin;

    public AutoMessageTask(Main m) {
        plugin = m;
    }

    public static void start() {
        new BukkitRunnable() {
            int i = 0;
            List<String> Messages = plugin.getConfig().getStringList("automessages");

            public void run() {
                if (i < Messages.size()) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("§c§lGERALT §8» §7" + Messages.get(i).replace("&", "§"));
                    }
                    i++;
                } else {
                    i = 0;
                }
            }
        }.runTaskTimer(plugin, 20L * plugin.getConfig().getInt("automessages-time"), 20L * plugin.getConfig().getInt("automessages-time"));
    }
}