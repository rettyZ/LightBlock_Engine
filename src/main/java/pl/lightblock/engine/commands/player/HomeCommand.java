package pl.lightblock.engine.commands.player;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.lightblock.engine.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class HomeCommand implements CommandExecutor {
    HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    private Main plugin;
    private FileConfiguration config;
    public HomeCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(" ");
            commandSender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/home)");
            commandSender.sendMessage(" ");
        } else {
            if(cooldowns.getOrDefault(p.getUniqueId().toString(), Long.valueOf(0)) > System.currentTimeMillis())
            {
                Date dateFromNow = new Date(cooldowns.getOrDefault(p.getUniqueId().toString(), Long.valueOf(0)));
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHOME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Tej komendy możesz użyć za:");
                p.sendMessage("§8» §e" + getDateDiff(new Date(System.currentTimeMillis()), dateFromNow, TimeUnit.SECONDS) + " §7sekund!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHOME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
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
                Location tp = new Location(plugin.getServer().getWorld(Objects.requireNonNull(config.getString("home." + p.getName() + ".world"))), config.getDouble("home." + p.getName() + ".x"), config.getDouble("home." + p.getName() + ".y"), config.getDouble("home." + p.getName() + ".z"));
                p.teleport(tp);
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHOME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Przeniesiono do domu!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHOME §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                Calendar date = Calendar.getInstance();
                date.add(Calendar.SECOND, config.getInt("HomeCooldown"));
                cooldowns.put(p.getUniqueId().toString(), date.getTimeInMillis());
            }
        }
        return false;
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
