package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/broadcast)");
            sender.sendMessage(" ");
        } else {
            Player p = (Player) sender;

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < strings.length; ++i) {
                sb.append(strings[i]).append(" ");
            }
            String msg = sb.toString().replace("&", "§");

            {
                if (!(p.hasPermission("lightblock.bc") || p.isOp())) {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6BROADCAST §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.bc§7)!");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6BROADCAST §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                } else {
                    if (!(strings.length > 1)) {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBROADCAST §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Prawidłowe użycie: /bc (title/bossbar/chat)");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBROADCAST §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return false;
                    } else {
                        if (strings[0].equalsIgnoreCase("title")) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.sendTitle("§eLIGHT§fBLOCK§e.PL", "§7" + msg, 10, 100, 10);
                            }
                        } else if (strings[0].equalsIgnoreCase("chat") || strings[0].equalsIgnoreCase("c")) {
                            Bukkit.broadcastMessage("§eLIGHT§fBLOCK§e.PL §8» §7" + msg);
                        } else {
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBROADCAST §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p.sendMessage("§8» §7Prawidłowe użycie: /bc (title/bossbar/chat)");
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBROADCAST §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            return false;
                        }   
                    }
                }
            }
        }
    return false;
    }
}