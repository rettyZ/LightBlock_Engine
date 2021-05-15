package pl.lightblock.engine.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class GodCommand implements CommandExecutor, Listener {
    private static List<Player> godmode = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        Player p = (Player) sender;
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/god)");
            sender.sendMessage(" ");
        } else {
            if(!(p.hasPermission("lightblock.god") || p.isOp())){
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.god§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            }
            else {
                if(godmode.contains(sender))
                {
                    godmode.remove(p);
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Tryb nieśmiertelności: §cwyłączony");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return true;
                } else {
                    godmode.add(p);
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Tryb nieśmiertelności: §awłączony");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGOD §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && godmode.contains(e.getEntity())) {
            e.setCancelled(true);
            ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getMaxHealth());
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        godmode.remove(e.getPlayer());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        godmode.remove(e.getPlayer());
    }
}
