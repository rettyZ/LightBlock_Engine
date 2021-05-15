package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pl.lightblock.engine.Main;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class LightBoxListener implements Listener {

    Main plugin;
    Random random = new Random();
    public LightBoxListener(Main plugin)
    {
        this.plugin = plugin;
    }

    FileConfiguration config;
    File lightcoin;

    @EventHandler
    public void onPlaceBox(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.EMERALD_ORE && e.getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY) && e.getItemInHand().getItemMeta().hasLore() && e.getItemInHand().getItemMeta().getDisplayName().equals("§eLight§fBox")) {
            lightcoin = new File(plugin.getDataFolder(), "lightcoin.yml");
            if (!lightcoin.exists()) {
                try {
                    lightcoin.createNewFile();
                } catch (IOException anal) {
                    anal.printStackTrace();
                }
            }
            if (lightcoin.exists()) {

                    e.getBlockPlaced().setType(Material.AIR);
                    Player p = e.getPlayer();
                    Location location = e.getBlock().getLocation().add(1, 0, 1);
                    switch (random.nextInt(5)) {
                        case 0: {
                            location.getWorld().dropItemNaturally(location, new ItemStack(Material.APPLE, 15));
                            e.getPlayer().sendMessage("§8» §cWylosowałeś x15 jabłko!");
                            break;
                        }
                        case 1: {
                            location.getWorld().dropItemNaturally(location, new ItemStack(Material.COOKED_PORKCHOP, 5));
                            e.getPlayer().sendMessage("§8» §cWylosowałeś x5 pieczony schab!");
                            break;
                        }
                        case 2: {
                            location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIAMOND, 5));
                            e.getPlayer().sendMessage("§8» §cWylosowałeś x5 diament!");
                            break;
                        }
                        case 3: {
                            location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIRT, 1));
                            e.getPlayer().sendMessage("§8» §cWylosowałeś x1 gówno psa!");
                            break;
                        }
                        case 4: {
                            config = YamlConfiguration.loadConfiguration(lightcoin);
                            config.set("money." + e.getPlayer().getName(), config.getInt("money." + e.getPlayer().getName(), 0) + 10);
                            try {
                                config.save(lightcoin);
                            } catch (IOException seks) {
                                seks.printStackTrace();
                            }
                            Bukkit.broadcastMessage("§8[§eLIGHT§fBOX§8] §7Gracz: §c" + e.getPlayer().getName() + "§7 wylosował §cx10 §eLight§fCoinsów§7!");
                            e.getPlayer().sendMessage("§8» §cWylosowałeś x10 §eLight§fCoinsów§7!");
                            break;
                        }
                    }
                }
            }
        }
    }