package pl.lightblock.engine.listeners;

import net.luckperms.api.*;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.commands.admin.chatmenu.ChatItemsMenu;
import java.util.*;

public class PrefixListener implements Listener
{
    Main plugin;
    LuckPerms luckPerms;
    ChatItemsMenu command;
    List<String> blockedMsg = Arrays.asList("#op", "#reload", "#stop");

    public PrefixListener(Main plugin, LuckPerms luckPerms, ChatItemsMenu command) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
        this.command = command;
    }

    @EventHandler
    public void Prefix(final AsyncPlayerChatEvent e)
    {
        final Player p = e.getPlayer();
        CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(p);
        String prefix = metaData.getPrefix();
        e.setCancelled(true);
        if ((!command.chatDisabled || e.getPlayer().hasPermission("lightblock.chatmanage") || e.getPlayer().isOp()) && !blockedMsg.contains(e.getMessage())) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String msg = e.getMessage().replace("&", "§");
                if(e.getMessage().contains("@" + player.getName()))
                {
                    player.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, SoundCategory.MASTER, 1, 1.5F);
                    msg = "§c§n§l§o" + msg;
                }
                String tocomawyslac = prefix + "§r §8| §7" + e.getPlayer().getName() + "§8: §7" + msg;
                p.sendMessage(tocomawyslac);
            }
        }
    }
}
