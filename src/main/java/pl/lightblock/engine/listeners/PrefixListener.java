package pl.lightblock.engine.listeners;

import net.luckperms.api.*;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
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
            String tocomawyslac = prefix + "§r §8| §7" + e.getPlayer().getName() + "§8: §7" + e.getMessage().replace("&", "§");
            Bukkit.broadcastMessage(tocomawyslac);
        }
    }
}
