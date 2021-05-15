package pl.lightblock.engine.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/fly)");
            sender.sendMessage(" ");
        } else {
            Player p = (Player) sender;

            if (!(p.hasPermission("lightblock.fly") || p.isOp())) {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.fly§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            } else {
                if(!(strings.length > 0)){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Prawidłowe użycie: /fly (off/on)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                }
                else{
                    if (strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("off")) {
                        p.setAllowFlight(false);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Tryb latania: §cwyłączony");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                    if (strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("on")) {
                        p.setAllowFlight(true);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Tryb latania: §awłączony");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    } else {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Prawidłowe użycie: /fly (off/on)");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eFLY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return false;
                    }
                }
            }
        }
        return false;
    }
}