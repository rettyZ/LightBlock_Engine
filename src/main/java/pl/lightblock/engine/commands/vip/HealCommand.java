package pl.lightblock.engine.commands.vip;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class HealCommand implements CommandExecutor {

    HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        Player p = (Player) sender;

        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/heal)");
            sender.sendMessage(" ");
        } else {
            if(!(p.hasPermission("lightblock.vip.heal") || p.isOp())){
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.vip.heal§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            }
            else {
                if(cooldowns.getOrDefault(p.getUniqueId().toString(), Long.valueOf(0)) > System.currentTimeMillis())
                {
                    Date dateFromNow = new Date(cooldowns.getOrDefault(p.getUniqueId().toString(), Long.valueOf(0)));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Tej komendy możesz użyć za:");
                    p.sendMessage("§8» §e" + getDateDiff(new Date(System.currentTimeMillis()), dateFromNow, TimeUnit.SECONDS) + " §7sekund!");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                } else {
                    p.getPlayer().setHealth(p.getPlayer().getMaxHealth());
                    p.getPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Uzdrowiono!");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eHEAL §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    Calendar date = Calendar.getInstance();
                    date.add(Calendar.SECOND, 10);
                    cooldowns.put(p.getUniqueId().toString(), date.getTimeInMillis());
                    return true;
                }
            }
        }
        return false;
    }
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
