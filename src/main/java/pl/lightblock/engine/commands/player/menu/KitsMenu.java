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
import org.bukkit.inventory.ItemStack;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.utils.ItemBuilder;
import pl.lightblock.engine.utils.PluginUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KitsMenu implements Listener {
    public static Map<String, Integer> starterKitCooldown = new HashMap<>();
    private Main plugin;
    private FileConfiguration config;
    File lightcoin;

    public KitsMenu(Main plugin) {
        this.plugin = plugin;
    }

    public static InventoryView showInv(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 9, "§8» §eSklep z zestawami");

        ItemBuilder freeKit = new ItemBuilder(Material.LEATHER_CHESTPLATE).setTitle("§8» §bZestaw podstawowy").addLores(Arrays.asList("§aDodaje Ci zestaw podstawowy (Można go pobierać co godzinę)", " ", "§7Koszt: 0 " + PluginUtils.getMoneyCurrency()));
        ItemBuilder pro = new ItemBuilder(Material.IRON_CHESTPLATE).setTitle("§8» §bZestaw premium").addLores(Arrays.asList("§aDodaje Ci zestaw podstawowy", " ", "§7Koszt: 100 " + PluginUtils.getMoneyCurrency()));
        ItemBuilder vip = new ItemBuilder(Material.DIAMOND_CHESTPLATE).setTitle("§8» §bZestaw VIP").addLores(Arrays.asList("§aDodaje ci zestaw VIP (Dostępny jedynie posiadając rangę VIP lub wyższą)", " ", "§7Koszt: 500 " + PluginUtils.getMoneyCurrency()));

        inv.setItem(3, freeKit.build());
        inv.setItem(4, pro.build());
        inv.setItem(5, vip.build());

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

        if("§8» §eSklep z zestawami".equalsIgnoreCase(e.getView().getTitle()))
        {
            Player p = (Player) e.getWhoClicked();
            loadConfig(p);

            int cost = 0;
            switch (e.getSlot()) {
                case 3:
                    cost = 0;
                    break;
                case 4:
                    cost = 100;
                    break;
                case 5:
                    cost = 500;
                    break;
            }
            boolean hasEnough = false;

            if (config.getInt("money." + p.getName()) >= cost || e.getSlot() == 3) {
                hasEnough = true;
                if (e.getSlot() != 3) {
                    config.set("money." + p.getName(), config.getInt("money." + p.getName(), 0) - cost);
                }
            } else {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie masz wymaganej ilości " + PluginUtils.getMoneyCurrency() + "!§7");
                p.sendMessage("§8» §7Brakuje Ci " + String.valueOf(cost - config.getInt("money." + p.getName())) + " monet");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
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
                    if (starterKitCooldown.containsKey(p.getName())) {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Nie możesz jeszcze wziąść tego zestawu!§7");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                    } else {
                        starterKitCooldown.put(p.getName(), 3600);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zakupiono zestaw podstawowy!§7");
                        p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                        p.getInventory().addItem(new ItemStack(Material.COOKED_PORKCHOP, 32));
                        p.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD, 1));
                    }
                }
                if (e.getSlot() == 4) {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Zakupiono zestaw premium!§7");
                    p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    p.getInventory().addItem(new ItemStack(Material.IRON_HELMET, 1));
                    p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS, 1));
                    p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS, 1));
                    p.getInventory().addItem(new ItemStack(Material.COOKED_PORKCHOP, 32));
                    p.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
                }
                if (e.getSlot() == 5) {
                    if (!(p.hasPermission("lightblock.vip.kit") || p.isOp())) {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.vip.kit§7)!");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                    } else {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zakupiono zestaw VIP!§7");
                        p.sendMessage("§8» §7Pozostała ilość monet " + PluginUtils.getMoneyCurrency() + ": " + config.getString("money." + p.getName()));
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eKITY §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
                        p.getInventory().addItem(new ItemStack(Material.COOKED_PORKCHOP, 32));
                        p.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
                    }
                }
            }
        }
    }

    public static void DoKitCheck()
    {
        for (HashMap.Entry<String, Integer> entry : starterKitCooldown.entrySet()) {
            if(entry.getValue() - 1 <= 0)
            {
                Bukkit.getPlayer(entry.getKey()).sendMessage("§aJuż możesz wziąść zestaw darmowy!");
                starterKitCooldown.remove(entry.getKey());
            } else {
                starterKitCooldown.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }
}
