package pl.lightblock.engine.commands.admin;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/gamemode)");
            sender.sendMessage(" ");

        }
        else
        {
            Player p = (Player) sender;

            if(!(p.hasPermission("lightblock.gamemode") || p.isOp())){
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.gamemode§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                return false;
            }
            else
            {
                if(strings.length != 1){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Prawidłowe użycie: /gamemode (0/1/2/3)");
                    p.sendMessage("§8» §7Prawidłowe użycie: /gamemode (s/c/a/sp)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    return false;
                }
                else
                {
                    if(strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("s")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zmieniłeś tryb gry na: §cSURVIVAL");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    } else if(strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("c")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zmieniłeś tryb gry na: §9CREATIVE");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    } else if(strings[0].equalsIgnoreCase("2") || strings[0].equalsIgnoreCase("a")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zmieniłeś tryb gry na: §dADVENTURE");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    } else if(strings[0].equalsIgnoreCase("3") || strings[0].equalsIgnoreCase("sp")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Zmieniłeś tryb gry na: §dSPECTATOR");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return true;
                    }
                    else
                    {
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        p.sendMessage("§8» §7Prawidłowe użycie: /gamemode (0/1/2/3)");
                        p.sendMessage("§8» §7Prawidłowe użycie: /gamemode (s/c/a/sp)");
                        p.sendMessage(" ");
                        p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eGAMEMODE §8★☆★☆★☆★☆★☆★☆");
                        p.sendMessage(" ");
                        return false;
                    }
                }
            }
        }
        return false;
    }
}