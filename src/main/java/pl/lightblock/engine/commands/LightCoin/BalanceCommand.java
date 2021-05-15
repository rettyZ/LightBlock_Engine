package pl.lightblock.engine.commands.LightCoin;

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

public class BalanceCommand implements CommandExecutor {
    private Main plugin;
    private FileConfiguration config;

    public BalanceCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        Player p = (Player) sender;
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/balance)");
            sender.sendMessage(" ");
        } else {
            File lightcoin = new File(plugin.getDataFolder(), "lightcoin.yml");
            if (!lightcoin.exists()) {
                try {
                    lightcoin.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = YamlConfiguration.loadConfiguration(lightcoin);
            if(config.getString("money." + p.getName()) == null)
            {
                config.set("money." + p.getName(), 0);
                try {
                    config.save(lightcoin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            p.sendMessage("§6§lStan konta: §7" + config.getString("money." + p.getName()) + " §eL§fC");
        }
        return false;
    }
}