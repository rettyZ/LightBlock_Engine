package pl.lightblock.engine.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.commands.player.SpawnCommand;

public class MoveListener implements Listener {
    SpawnCommand spawn;
    public MoveListener(SpawnCommand spawn)
    {
        this.spawn = spawn;
    }
    @EventHandler
    public void OnMove(PlayerMoveEvent e) {
        if(spawn.taskmap.containsKey(e.getPlayer().getName()))
        {
            BukkitTask task = spawn.taskmap.get(e.getPlayer().getName());
            if(!task.isCancelled())
            {
                task.cancel();
                spawn.taskmap.remove(e.getPlayer().getName());
                e.getPlayer().sendMessage("§8» §cPoruszyłeś się, teleportacja anulowana!");
            }
        }
    }
}
