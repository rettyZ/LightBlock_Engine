package pl.lightblock.engine.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.lightblock.engine.utils.VersionInfo;

public class VersionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        p.sendMessage(" ");
        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6INFORMACJE §8★☆★☆★☆★☆★☆★☆");
        p.sendMessage(" ");
        p.sendMessage("§8» §7Wersja serwera: " + VersionInfo.ServerVersion);
        p.sendMessage("§8» §7Data wydania: " + VersionInfo.ReleaseDate.toString());
        p.sendMessage("§8» §7Opis zmian: " + VersionInfo.Changelog);
        p.sendMessage(" ");
        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §6INFORMACJE §8★☆★☆★☆★☆★☆★☆");
        p.sendMessage(" ");
        return true;
    }
}
