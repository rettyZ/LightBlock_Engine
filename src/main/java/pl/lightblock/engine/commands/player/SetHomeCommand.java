package pl.lightblock.engine.commands.player;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.lightblock.engine.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

public class SetHomeCommand implements CommandExecutor {
    private Main plugin;
    private FileConfiguration config;
    public SetHomeCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(" ");
            commandSender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/sethome)");
            commandSender.sendMessage(" ");
        } else {
            File locationConfig = new File(plugin.getDataFolder(), "locations.yml");
            if(!locationConfig.exists())
            {
                try {
                    locationConfig.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = YamlConfiguration.loadConfiguration(locationConfig);

            config.createSection("home." + p.getName() + ".x");
            config.createSection("home." + p.getName() + ".y");
            config.createSection("home." + p.getName() + ".z");
            config.createSection("home." + p.getName() + ".world");
            config.set("home." + p.getName() + ".x", p.getLocation().getX());
            config.set("home." + p.getName() + ".y", p.getLocation().getY());
            config.set("home." + p.getName() + ".z", p.getLocation().getZ());
            config.set("home." + p.getName() + ".world", p.getLocation().getWorld().getName());
            p.sendMessage(" ");
            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETHOME §8★☆★☆★☆★☆★☆★☆");
            p.sendMessage(" ");
            p.sendMessage("§8» §7Ustawiono dom!");
            p.sendMessage(" ");
            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eSETHOME §8★☆★☆★☆★☆★☆★☆");
            p.sendMessage(" ");
            try {
                config.save(locationConfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
