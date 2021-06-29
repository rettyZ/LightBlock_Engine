package pl.lightblock.engine.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.managers.Scoreboard;
import net.luckperms.api.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class JoinQuitListener implements Listener {

    private FileConfiguration config;
    LuckPerms luckPerms;

    public JoinQuitListener(Main plugin, LuckPerms luckPerms)
    {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }
    Main plugin;

    final Scoreboard s = new Scoreboard();

    @SuppressWarnings("static-access")
    @EventHandler
    public void JoinListener(PlayerJoinEvent e) {
        File blacklistedConfig = new File(plugin.getDataFolder(), "blacklisted.yml");
        if (!blacklistedConfig.exists()) {
            try {
                blacklistedConfig.createNewFile();
            } catch (IOException anal) {
                anal.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(blacklistedConfig);

        if(config.contains("blacklisted." + e.getPlayer().getName())){
            e.getPlayer().kickPlayer("§8» §eLIGHT§fBLOCK§e.PL §8«\n\n§c✖ §7Znajdujesz się na blackliście! §c✖\n§8◈ §7Powód: §c" + config.getString("blacklisted." + e.getPlayer().getName() + ".powod") + " §8◈\n\n§8¿ §cChcesz wyjaśnić sprawę §8?\nNapisz do któregoś z nas na discord:\n§8– §7barthvisuals#7981\n§8– §7nolemretaW#2137");
        }

        s.createScoreboard(e.getPlayer());
        s.updateScoreboard();

        boolean adminjoin_status = plugin.getConfig().getBoolean("adminjoin-on");

        if(!(adminjoin_status == true)){
            e.setJoinMessage(null);
        }
        else
        {
            final Player p = e.getPlayer();
            if(!(p.hasPermission("lightblock.adminjoin") || p.isOp())){
                e.setJoinMessage(null);
            }
            else
            {
                if(p.getDisplayName().equals("BarthVisuals") || (p.getDisplayName().equals("nolemretaW"))){
                    e.setJoinMessage(null);
                    Bukkit.broadcastMessage("§8» §eLIGHT§fBLOCK§e.PL");
                    Bukkit.broadcastMessage("§8» §7Właściciel serwera §e" + p.getDisplayName() + " §7dołączył na serwer!");
                }
                else
                {
                    e.setJoinMessage(null);
                    Bukkit.broadcastMessage("§8» §eLIGHT§fBLOCK§e.PL");
                    Bukkit.broadcastMessage("§8» §7Administrator §c" + p.getDisplayName() + " §7dołączył na serwer!");
                }
            }
        }
    }
    @SuppressWarnings("static-access")
    @EventHandler
    public void QuitListener(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()) {
            s.createScoreboard(player);
        }
        if(AntiQuitListener.PlayerQuitTimeout.containsKey(e.getPlayer().getName()))
        {
            e.getPlayer().setHealth(0);
            AntiQuitListener.PlayerQuitTimeout.remove(e.getPlayer().getName());
            Bukkit.broadcastMessage("§cGracz " + e.getPlayer().getName() + " wylogował się podczas walki!");
        }
    }
}