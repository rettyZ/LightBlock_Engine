package pl.lightblock.engine.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.lightblock.engine.Main;

public class SSGListener implements Listener {

    private Main plugin;
    public SSGListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlaceBedrock (BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.BEDROCK){
            if(!(e.getPlayer().hasPermission("lightblock.placebedrock") || e.getPlayer().isOp())){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§8[§cSSG§8] §7Stawianie bloku: §cBEDROCK §7jest §cZABLOKOWANE§7!");
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(players.hasPermission("lightblock.ssg.view") || players.isOp()){
                        players.sendMessage("§8[§cSSG§8] §7Gracz: §c" + e.getPlayer().getName() + " §7próbował postawić: §cBEDROCK");
                    }
                    else
                    {
                        return;
                    }
                }
            }
            else
            {
                e.setCancelled(false);
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(players.hasPermission("lightblock.ssg.view") || players.isOp()){
                        players.sendMessage("§8[§cSSG§8] §7Administrator: §c" + e.getPlayer().getName() + " §7powstawił: §cBEDROCK");
                    }
                    else
                    {
                        return;
                    }
                }
            }
        }
    }
}