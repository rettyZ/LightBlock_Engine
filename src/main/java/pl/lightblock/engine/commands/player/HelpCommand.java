package pl.lightblock.engine.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/help)");
            sender.sendMessage(" ");
        } else {
            Player p = (Player) sender;

            if (!(strings.length > 0)) {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §eLIGHT§fHARD§e.PL");
                p.sendMessage("§8» §7Tryb serwera: §eSkyBlock");
                p.sendMessage("§8» §7Wersja serwera: §e1.8.8");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Aby uzyskać inną pomoc:");
                p.sendMessage("§8» §7/help (§ekomendy/inne§7)");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return true;
            } else {
                if(strings[0].equalsIgnoreCase("komendy")){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7/x - cos tam robi");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return true;
                }
                else
                {
                    if(strings[0].equalsIgnoreCase("inne")){
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Inne");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                    else
                    {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §eLIGHT§fHARD§e.PL");
                        p.sendMessage("§8» §7Tryb serwera: §eSkyBlock");
                        p.sendMessage("§8» §7Wersja serwera: §e1.8.8");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Aby uzyskać inną pomoc:");
                        p.sendMessage("§8» §7/help (§ekomendy/inne§7)");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELP §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                }
            }
        }
    return false;
    }
}