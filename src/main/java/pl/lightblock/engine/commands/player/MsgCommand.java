package pl.lightblock.engine.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
            if(sender instanceof ConsoleCommandSender) {
                sender.sendMessage(" ");
                sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/msg)");
                sender.sendMessage(" ");
            }
            else
            {
                Player p = (Player) sender;
                if(!(p.hasPermission("lightblock.msg"))){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6MSG §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.msg§7)!");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6MSG §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                }
                else
                {
                    if(strings.length < 2){
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eMSG §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Prawidłowe użycie: /msg <gracz> <wiadomość>");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eMSG §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return false;
                    }
                    else
                    {
                        String message = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                        Player reciever = Bukkit.getPlayer(strings[0]);
                        if(reciever == null)
                        {
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eMSG §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p.sendMessage("§8» §7Nie znaleziono takiego gracza!");
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eMSG §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                        } else {
                            reciever.playSound(reciever.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 2f);
                            reciever.sendMessage(ChatColor.GOLD + "[OD]" + ChatColor.WHITE + "<" + p.getName() + "> " + message);
                            p.sendMessage(ChatColor.GOLD + "[DO]" + ChatColor.WHITE + "<" + reciever.getName() + "> " + message);
                        }
                    }
                }
            }
            return false;
        }
}
