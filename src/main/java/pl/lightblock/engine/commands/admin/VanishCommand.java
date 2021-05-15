package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;


public class VanishCommand implements CommandExecutor, Listener {
    public static ArrayList<String> hiddenUsernames = new ArrayList<>();

    public void VanishP(Player p) {
        for (Player players : p.getServer().getOnlinePlayers()) {
            hiddenUsernames.add(p.getName());
            players.hidePlayer(p);
            if (players.hasPermission("lightblock.vanish") || players.isOp()) {
                players.showPlayer(p);
            }
        }
    }

    public void unVanishP(Player p) {
        for (Player players : p.getServer().getOnlinePlayers()) {
            hiddenUsernames.remove(p.getName());
            players.showPlayer(p);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!(p.hasPermission("lightblock.vanish") || p.isOp())) {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.vanish§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
            } else {
                if (strings.length == 0) {
                    if (hiddenUsernames.contains(p.getName())) {
                        unVanishP(p);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Vanish: §cwyłączony");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("lightblock.vanish") || player.isOp()) {
                                player.sendMessage("§8[§cVANISH§8] §c" + p.getDisplayName() + "§7 wyłączył vanish");
                            }
                        }
                    } else {
                        VanishP(p);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Vanish: §awłączony");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("lightblock.vanish") || player.isOp()) {
                                player.sendMessage("§8[§cVANISH§8] §c" + p.getDisplayName() + "§7 włączył vanish");
                            }
                        }
                    }
                } else if (strings.length == 1) {
                    Player p1 = Bukkit.getServer().getPlayer(strings[0]);
                    if (p1 != null) {
                        if (hiddenUsernames.contains(p1.getName())) {
                            unVanishP(p1);
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p.sendMessage("§8» §7Wyłączyłeś vanish graczowi: §c" + p1.getDisplayName());
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8» §7Administrator: §c" + p.getDisplayName() + "§7 wyłączył ci vanish");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p1.sendMessage(" ");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.hasPermission("lightblock.vanish") || player.isOp()) {
                                    player.sendMessage("§8[§cVANISH§8] §c" + p.getDisplayName() + "§7 wyłączył vanish graczowi: §c" + p1.getDisplayName());
                                }
                            }
                        } else {
                            VanishP(p1);
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p.sendMessage("§8» §7Włączyłeś vanish graczowi: §c" + p1.getDisplayName());
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8» §7Administrator: §c" + p.getDisplayName() + "§7 włączył ci vanish");
                            p1.sendMessage(" ");
                            p1.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                            p1.sendMessage(" ");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.hasPermission("lightblock.vanish") || player.isOp()) {
                                    player.sendMessage("§8[§cVANISH§8] §c" + p.getDisplayName() + "§7 włączył vanish graczowi: §c" + p1.getDisplayName());
                                }
                            }
                        }
                    } else {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Nie znaleziono gracza!");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                    }
                } else {
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Prawidłowe użycie: /vanish (gracz)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eVANISH §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                }
            }
        } else {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/god)");
            sender.sendMessage(" ");
        }
        return false;
    }

    @EventHandler
    public void vanishPlayerQuit(PlayerQuitEvent event) {
        if (hiddenUsernames.contains(event.getPlayer().getName())) {
            event.getPlayer().showPlayer(event.getPlayer());
        }
    }

    @EventHandler
    public void VanishNoInteract(PlayerInteractEvent event) {
        if (hiddenUsernames.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }
}