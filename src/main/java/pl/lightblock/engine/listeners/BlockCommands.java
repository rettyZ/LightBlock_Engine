package pl.lightblock.engine.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.lightblock.engine.Main;

public class BlockCommands implements Listener {
    Main plugin;
    public BlockCommands(Main plugin)
    {
        this.plugin = plugin;
    }
    @EventHandler
    private void onCmd(PlayerCommandPreprocessEvent e){
        if(plugin.getConfig().getList("blockedcommands").contains(e.getMessage())){
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cKomenda jest zablokowana!");
        }
    }
}