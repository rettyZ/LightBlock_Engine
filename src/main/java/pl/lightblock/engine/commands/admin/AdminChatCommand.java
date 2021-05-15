package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AdminChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/adminchat)");
            sender.sendMessage(" ");
        } else {
            Player p = (Player) sender;
            if (!(p.hasPermission("lightblock.adminchat") || p.isOp())) {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eADMINCHAT §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.adminchat§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eADMINCHAT §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            } else {
                if(!(strings.length > 0)){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eADMINCHAT §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Poprawne użycie: /adminchat (wiadomość)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eADMINCHAT §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                }
                else
                {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        String message = String.join(" ", strings);
                        p.sendMessage("§8[§eAC§8] §c" + p.getDisplayName() + " §8» §7" + message.replace("&", "§"));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
