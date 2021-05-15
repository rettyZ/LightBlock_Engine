package pl.lightblock.engine.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LightBoxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        Player p = (Player)sender;

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[LightBlock] Tej komendy uzywac mozna tylko z poziomu gracza! (/lightbox)");
            sender.sendMessage(" ");
        } else {
            if (!(p.hasPermission("lightblock.lightbox") || p.isOp()))
            {
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§elightblock.lightbox§7)!");
                p.sendMessage(" ");
                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                p.sendMessage(" ");
            }
            else
            {
                if(!(strings.length > 0)){
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                    p.sendMessage("§8» §7Prawidłowe użycie: /lightbox give (nick gracza) (ilosc)");
                    p.sendMessage("§8» §7Prawidłowe użycie: /lightbox all (ilosc)");
                    p.sendMessage(" ");
                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                    p.sendMessage(" ");
                }
                else
                {
                    if(strings[0].equalsIgnoreCase("all")){
                        if(strings.length == 2){
                            if(Integer.parseInt(strings[1]) <= 0 || Double.isNaN(Double.parseDouble(strings[1])))
                            {
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                                p.sendMessage("§8» §7Podaj prawidłową liczbę!");
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                            } else
                            {
                                for(Player players : Bukkit.getOnlinePlayers()){
                                    //tutaj kod na boxy
                                    players.sendMessage("§8[§eLIGHT§fBOX§8] §7Cały serwer otrzymał §cx" + strings[1] + "§7 LightBoxów!");
                                    players.sendMessage("§8[§eLIGHT§fBOX§8] §7Od administratora: §c" + p.getName());
                                    ItemStack stack = new ItemStack(Material.EMERALD_ORE, Integer.parseInt(strings[1]));
                                    ItemMeta meta = stack.getItemMeta();
                                    meta.setDisplayName("§eLight§fBox");
                                    ArrayList<String> lore = new ArrayList<String>();
                                    lore.add("§cUnikatowy §eLight§fBox");
                                    lore.add("§aPo postawieniu otrzymasz losowy przedmiot!");
                                    meta.setLore(lore);
                                    meta.addEnchant(Enchantment.DURABILITY, 69, true);

                                    stack.setItemMeta(meta);
                                    players.getInventory().addItem(stack);
                                }
                            }
                        }
                        else
                        {
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                            p.sendMessage("§8» §7Prawidłowe użycie: /lightbox give (nick gracza) (ilosc)");
                            p.sendMessage("§8» §7Prawidłowe użycie: /lightbox all (ilosc)");
                            p.sendMessage(" ");
                            p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                            p.sendMessage(" ");
                        }
                    }
                    if(strings[0].equalsIgnoreCase("give")){
                        if(strings.length == 3){
                            Player playerGive = Bukkit.getPlayer(strings[1]);
                            if(playerGive == null) {
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                                p.sendMessage("§8» §7Nie znaleziono takiego gracza!");
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                            }
                            else
                            {
                                if(Integer.parseInt(strings[2]) <= 0 || Double.isNaN(Double.parseDouble(strings[2])))
                                {
                                    p.sendMessage(" ");
                                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                    p.sendMessage(" ");
                                    p.sendMessage("§8» §7Podaj prawidłową liczbę!");
                                    p.sendMessage(" ");
                                    p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                    p.sendMessage(" ");
                                } else
                                {
                                    playerGive.sendMessage("§8[§eLIGHT§fBOX§8] §7Otrzymałeś §cx" + strings[2] + "§7 LightBoxów!");
                                    playerGive.sendMessage("§8[§eLIGHT§fBOX§8] §7Od administratora: §c" + p.getName());
                                    p.sendMessage("§8[§eLIGHT§fBOX§8] §7Gracz: §c" + playerGive.getName() + " §7otrzymał od ciebie: §cx" + strings[2] + " §7LightBoxów!");
                                    ItemStack stack = new ItemStack(Material.EMERALD_ORE, Integer.parseInt(strings[2]));
                                    ItemMeta meta = stack.getItemMeta();
                                    meta.setDisplayName("§eLight§fBox");
                                    ArrayList<String> lore = new ArrayList<String>();
                                    lore.add("§cUnikatowy §eLight§fBox");
                                    lore.add("§aPo postawieniu otrzymasz losowy przedmiot!");
                                    meta.setLore(lore);
                                    meta.addEnchant(Enchantment.DURABILITY, 69, true);

                                    stack.setItemMeta(meta);
                                    playerGive.getInventory().addItem(stack);
                                }
                            }
                        }
                        else
                        {
                            if(!(strings.length == 1)){
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                                p.sendMessage("§8» §7Prawidłowe użycie: /lightbox give (nick gracza) (ilosc)");
                                p.sendMessage("§8» §7Prawidłowe użycie: /lightbox all (ilosc)");
                                p.sendMessage(" ");
                                p.sendMessage("§8★☆★☆★☆★☆★☆★☆ §eLIGHT§fBOX §8★☆★☆★☆★☆★☆★☆");
                                p.sendMessage(" ");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
