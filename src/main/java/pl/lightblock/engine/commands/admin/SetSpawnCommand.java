package pl.lightblock.engine.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.lightblock.engine.Main;

import java.io.File;
import java.io.IOException;

public class SetSpawnCommand implements CommandExecutor {
    Main plugin;
    public SetSpawnCommand(Main plugin)
    {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(" ");
            commandSender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/sethome)");
            commandSender.sendMessage(" ");
        } else {
            Player p = (Player) commandSender;
            if (!(p.hasPermission("lightblock.setspawn") || p.isOp()))
            {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETSPAWN §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.setspawn§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETSPAWN §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
            } else {
                plugin.getConfig().set("spawn.x", p.getLocation().getX());
                plugin.getConfig().set("spawn.y", p.getLocation().getY());
                plugin.getConfig().set("spawn.z", p.getLocation().getZ());
                plugin.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
                plugin.saveConfig();
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETSPAWN §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Ustawiono spawn!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETSPAWN §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
            }
        }
        return false;
    }
}
