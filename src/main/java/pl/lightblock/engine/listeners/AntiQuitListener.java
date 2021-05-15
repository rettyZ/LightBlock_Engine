package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.lightblock.engine.Main;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Random;

public class AntiQuitListener implements Listener {
    static HashMap<String, Integer> PlayerQuitTimeout = new HashMap<>();

    Main plugin;
    public AntiQuitListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player)
        {
            double attackedHealth = round(Bukkit.getPlayer(event.getEntity().getName()).getHealth() - event.getDamage(), 1);
            double attackerHealth = round(Bukkit.getPlayer(event.getDamager().getName()).getHealth(), 1);
            if(attackedHealth < 0)
            {
                attackedHealth = 0;
            }
            event.getEntity().sendMessage("§8[§c" + attackedHealth +" ❤§8] §a" + event.getEntity().getName() + "§8 « [§c" + attackerHealth +" ❤§8] §c " + event.getDamager().getName());
            event.getDamager().sendMessage("§8[§c" + attackerHealth +" ❤§8] §a" + event.getDamager().getName() + "§8 » " + " [§c" + attackedHealth +" ❤§8] §c " + event.getEntity().getName());
            if(!(PlayerQuitTimeout.containsKey(event.getEntity().getName())))
            {
                event.getEntity().sendMessage("§cJesteś podczas walki! Nie wylogowywuj się!");
            }
            if(!(PlayerQuitTimeout.containsKey(event.getDamager().getName())))
            {
                event.getDamager().sendMessage("§cJesteś podczas walki! Nie wylogowywuj się!");
            }
            PlayerQuitTimeout.put(event.getEntity().getName(), plugin.getConfig().getInt("antilogout-delay"));
            PlayerQuitTimeout.put(event.getDamager().getName(), plugin.getConfig().getInt("antilogout-delay"));
            if(((Player) event.getEntity()).getHealth() - event.getDamage() <= 0)
            {
                Random r = new Random();
                File lightcoin = new File(plugin.getDataFolder(), "lightcoin.yml");
                if (!lightcoin.exists()) {
                    try {
                        lightcoin.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileConfiguration config = YamlConfiguration.loadConfiguration(lightcoin);
                int money = r.nextInt(plugin.getConfig().getInt("killreward-max") - plugin.getConfig().getInt("killreward-min")) + plugin.getConfig().getInt("killreward-min");
                event.getDamager().sendMessage("§6" + money + " §eL§fC §cza zabójstwo gracza " + event.getEntity().getName());
                config.set("money." + event.getDamager().getName(), money);
                try {
                    config.save(lightcoin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DoAntiLogoutCheck()
    {
        for (HashMap.Entry<String, Integer> entry : PlayerQuitTimeout.entrySet()) {
            if(entry.getValue() - 1 <= 0)
            {
                Bukkit.getPlayer(entry.getKey()).sendMessage("§aNie jesteś już podczas walki, możesz się wylogować");
                PlayerQuitTimeout.remove(entry.getKey());
            } else {
                PlayerQuitTimeout.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
