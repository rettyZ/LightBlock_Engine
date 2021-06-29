package pl.lightblock.engine.commands.player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HelpOpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/helpop)");
            sender.sendMessage(" ");
        } else {
            Player p = (Player) sender;
            if (!(strings.length > 0)) {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELPOP §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Poprawne użycie: /helpop (wiadomość)");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHELPOP §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            } else {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if(player.hasPermission("lightblock.helpop.view") || player.isOp())
                    {
                        player.sendMessage(" ");
                        player.sendMessage(" ");
                        String message = String.join(" ", strings);
                        TextComponent messageclick = new TextComponent("§8[§cHELPOP§8] §7Gracz: §c"+ p.getName() + "\n" + "§8[§cHELPOP§8] §7Wiadomość: §c" + message.replace("&", "§"));
                        messageclick.setColor(ChatColor.RED);
                        messageclick.setBold(true);
                        messageclick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getDisplayName()));
                        messageclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("Kliknij aby przeteleportować się do gracza.").color(ChatColor.GOLD).italic(true).create()));
                        player.spigot().sendMessage(messageclick);
                        player.sendMessage(" ");
                        player.sendMessage(" ");
                    }
                });
            }
            sender.sendMessage("§aWysłano wiadomość do administracji!");
        }
    return false;
    }
}
