package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        Player p = (Player) sender;
        if (!(strings.length > 0)) {
            p.sendMessage(" ");
            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
            p.sendMessage(" ");
            p.sendMessage("§8» §7Prawidłowe użycie: /time (night/day)");
            p.sendMessage(" ");
            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
            p.sendMessage(" ");
            return false;
        } else {
            if(!(p.hasPermission("lightblock.time"))){
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.time§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            }
            else{
                if (strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("night") || strings[0].equalsIgnoreCase("noc")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        p.getWorld().setTime(13000);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Czas został zmieniony na: §1noc");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                }
                if (strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("day") || strings[0].equalsIgnoreCase("dzien")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        p.getWorld().setTime(1000);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Czas został zmieniony na: §edzień");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                } else {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Prawidłowe użycie: /time (night/day)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eTIME §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                }
            }
        }
    return false;
    }
}