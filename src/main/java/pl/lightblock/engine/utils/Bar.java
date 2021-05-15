package pl.lightblock.engine.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import pl.lightblock.engine.Main;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bar {
    private int taskID = -1;
    private final Main plugin;
    private BossBar bar;

    public Bar(Main plugin)
    {
        this.plugin = plugin;
    }

    public void addPlayer(Player player)
    {
        bar.addPlayer(player);
    }

    public BossBar getBar()
    {
        return bar;
    }

    public void createBar()
    {
        bar = Bukkit.createBossBar("LIGHTBLOCK.PL", BarColor.YELLOW, BarStyle.SEGMENTED_20);
        bar.setVisible(true);
        cast();
    }

    public void cast()
    {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int count = -1;
            double progress = 1.0;
            double time = 1.0 / 60;
            @Override
            public void run() {
                bar.setProgress(progress);
                switch(count)
                {
                    case -1:
                        break;
                    case 0:
                        bar.setTitle("Zajrzyj na naszego fanpage: fb.com/LightBlock");
                        break;
                    case 1:
                        bar.setTitle("Potrzebujesz pomocy? Użyj: /helpop (wiadomość)");
                        break;
                    case 2:
                    default:
                        bar.setTitle("Grasz na serwerze LIGHTBLOCK.PL");
                        count = -1;
                        break;
                }

                progress = progress - time;
                if(progress <= 0)
                {
                    count++;
                    progress = 1.0;
                }
            }

        }, 0, 5);
    }
}