package pl.lightblock.engine.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.lightblock.engine.commands.player.menu.EffectsMenu;

public class EffectsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/chat)");
            sender.sendMessage(" ");
            return false;
        } else {
            Player p = (Player) sender;
            EffectsMenu.showInv((Player) sender);
            return true;
        }
    }
}
