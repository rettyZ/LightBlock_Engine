package pl.lightblock.engine.commands.player.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.utils.ItemBuilder;

import pl.lightblock.engine.utils.PluginUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class EffectsMenu implements Listener {
    private Main plugin;
    private FileConfiguration config;
    File lightcoin;

    public EffectsMenu(Main plugin) {
        this.plugin = plugin;
    }

    public static InventoryView showInv(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 9, "§8» §eSklep z efektami");

        ItemBuilder speed = (new ItemBuilder(Material.POTION, 1)).setTitle("§8» §bPrędkość").addLores(Arrays.asList("§aDodaje Ci prędkość 2 na 2 minuty", " ", "§7Koszt: 15 " + PluginUtils.getMoneyCurrency()));
        ItemBuilder fireResistance = (new ItemBuilder(Material.POTION, 1)).setTitle("§8» §6Odporność na ogień").addLores(Arrays.asList("§aDodaje Ci odporność na ogień na 30 sekund", " ", "§7Koszt: 5 " + PluginUtils.getMoneyCurrency()));
        ItemBuilder regeneration = (new ItemBuilder(Material.POTION, 1)).setTitle("§8» §cRegeneracja").addLores(Arrays.asList("§aDodaje Ci regeneracje 2 na minutę", " ", "§7Koszt: 10 " + PluginUtils.getMoneyCurrency()));

        inv.setItem(3, speed.build());
        inv.setItem(4, fireResistance.build());
        inv.setItem(5, regeneration.build());

        return p.openInventory(inv);
    }

    private void loadConfig(Player p)
    {
        lightcoin = new File(plugin.getDataFolder(), "lightcoin.yml");
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
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) {

        if("§8» §eSklep z efektami".equalsIgnoreCase(e.getView().getTitle())) {
            Player p = (Player) e.getWhoClicked();
            loadConfig(p);

            int cost = 0;
            switch (e.getSlot()) {
                case 3:
                    cost = 15;
                    break;
                case 4:
                    cost = 5;
                    break;
                case 5:
                    cost = 10;
                    break;
            }
            boolean hasEnough = false;

            if (config.getInt("money." + p.getName()) >= cost) {
                hasEnough = true;
                config.set("money." + p.getName(), config.getInt("money." + p.getName(), 0) - cost);
            } else {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie masz wymaganej ilości " + PluginUtils.getMoneyCurrency() + "!§7");
                p.sendMessage("§8» §7Brakuje Ci " + String.valueOf(cost - config.getInt("money." + p.getName())) + " monet");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
            }
            try {
                config.save(lightcoin);
            } catch (IOException seks) {
                seks.printStackTrace();
            }

            if (hasEnough) {
                e.setCancelled(true);

                if (e.getSlot() == 3) {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Zakupiono prędkość 2 na 2 minuty!§7");
                    p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 2, 1));

                }
                if (e.getSlot() == 4) {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Zakupiono odporność na ogień na 30 sekund!§7");
                    p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 30, 0));
                }
                if (e.getSlot() == 5) {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Zakupiono regenerację 2 na minutę!§7");
                    p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eEFEKTY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60, 1));
                }
            }
        }
    }
}
