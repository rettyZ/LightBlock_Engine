package pl.lightblock.engine.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.lightblock.engine.commands.admin.chatmenu.ChatItemsMenu;

public class ChatCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/chat)");
            sender.sendMessage(" ");
            return false;
        } else {
            Player p = (Player) sender;
            if(!(p.hasPermission("lightblock.chatmanage") || p.isOp())){
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eChat §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.chatmanage§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eChat §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            } else {
                ChatItemsMenu.showInv((Player) sender);
            }
            return true;
        }
    }
}