package pl.lightblock.engine.managers;

import net.luckperms.api.*;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import pl.lightblock.engine.Main;
import pl.lightblock.engine.commands.admin.chatmenu.ChatItemsMenu;

import java.util.Arrays;
import java.util.List;

public class Scoreboard {


    public void createScoreboard(Player p){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Score", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§eLIGHT§fBLOCK§e.PL");

        Score score2 = obj.getScore(" ");
        score2.setScore(2);

        Score score3 = obj.getScore("§7Jesteś połączony z trybem: §eSkyBlock");
        score3.setScore(3);

        Score score4 = obj.getScore("§7Twój nick: §e" + p.getDisplayName());
        score4.setScore(4);
        /*
        Score score5 = obj.getScore("§7Gracze online: §e" + p.get);
        score5.setScore(5);
         */

        Score score6 = obj.getScore(" ");
        score6.setScore(6);

        p.setScoreboard(board);
    }
    public void updateScoreboard(){
        for(Player players : Bukkit.getOnlinePlayers()){
            createScoreboard(players);
        }
    }
}
