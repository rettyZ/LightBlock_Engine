package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.lightblock.engine.Main;

import java.io.File;
import java.io.IOException;

public class BlacklistCommand implements CommandExecutor {

    private FileConfiguration config;

    public BlacklistCommand(Main plugin) {
        this.plugin = plugin;
    }

    Main plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File blacklistedConfig = new File(plugin.getDataFolder(), "blacklisted.yml");
        if (!blacklistedConfig.exists()) {
            try {
                blacklistedConfig.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(blacklistedConfig);

        if (sender.getName().equals("BarthVisuals") || (sender.getName().equals("nolemretaW")) || (sender.getName().equals("plugin_yml")) || sender instanceof ConsoleCommandSender) {
            if (args.length > 1) {
                if(args.length >= 2){
                    if (args[0].equalsIgnoreCase("add")) {
                        config.createSection("blacklisted." + args[1] + ".powod");

                        boolean noreason;
                        if(args.length == 3)
                        {
                            config.set("blacklisted." + args[1] + ".powod", args[2].toString());
                            noreason = false;
                        } else {
                            config.set("blacklisted." + args[1] + ".powod", "<nie podano powodu>");
                            noreason = true;
                        }
                        if(Bukkit.getOfflinePlayer(args[1]).isOnline())
                        {
                            Player player2 = Bukkit.getServer().getPlayer(args[1]);
                            player2.kickPlayer("§8» §eLIGHT§fBLOCK§e.PL §8«\n\n§c✖ §7Znajdujesz się na blackliście! §c✖\n§8◈ §7Powód: §c" + config.getString("blacklisted." + args[1] + ".powod") + " §8◈\n\n§8¿ §cChcesz wyjaśnić sprawę §8?\nNapisz do któregoś z nas na discord:\n§8– §7barthvisuals#7981\n§8– §7nolemretaW#2137");
                        }
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (player.hasPermission("lightblock.blacklist.view") || player.isOp()) {
                                if(noreason)
                                {
                                    player.sendMessage("§8[§4BLACKLIST§8] §c" + sender.getName() + "§7 dodał do blacklisty gracza: §c" + args[1]);
                                } else {
                                    player.sendMessage("§8[§4BLACKLIST§8] §c" + sender.getName() + "§7 dodał do blacklisty gracza: §c" + args[1] + "§7 z powodu: §c" + args[2].toString());
                                }
                            }
                        }

                        try {
                            config.save(blacklistedConfig);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        config.set("blacklisted." + args[1], null);
                        for (Player player : Bukkit.getOnlinePlayers())
                            if (player.hasPermission("lightblock.blacklist.view") || player.isOp()) {
                                player.sendMessage("§8[§4BLACKLIST§8] §c" + sender.getName() + "§7 usunął z blacklisty gracza: §c" + args[1]);
                            }
                            try {
                                config.save(blacklistedConfig);
                            } catch (IOException e) {
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                sender.sendMessage(" ");
                sender.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBLACKLIST §8★☆★☆★☆★☆★☆★☆");
                sender.sendMessage(" ");
                sender.sendMessage("§8» §7Prawidłowe użycie: /blacklist add (nick gracza) (powód)");
                sender.sendMessage("§8» §7Prawidłowe użycie: /blacklist remove (nick gracza)");
                sender.sendMessage(" ");
                sender.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBLACKLIST §8★☆★☆★☆★☆★☆★☆");
                sender.sendMessage(" ");
            }
        } else {
            sender.sendMessage(" ");
            sender.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBLACKLIST §8★☆★☆★☆★☆★☆★☆");
            sender.sendMessage(" ");
            sender.sendMessage("§8» §7Nie możesz tego zrobić!");
            sender.sendMessage(" ");
            sender.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eBLACKLIST §8★☆★☆★☆★☆★☆★☆");
            sender.sendMessage(" ");
        }
        return false;
    }
}
