package pl.lightblock.engine.commands.admin.chatmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import pl.lightblock.engine.utils.ItemBuilder;

public class ChatItemsMenu implements Listener {

    public static boolean chatDisabled = false;

    public static InventoryView showInv(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 9, "§8» §cZarządzanie czatem");
        ItemBuilder ChatOff = (new ItemBuilder(Material.ENCHANTED_BOOK, 1)).setTitle("§8» §cWyłącz");
        ItemBuilder ChatOn = (new ItemBuilder(Material.ENCHANTED_BOOK, 1)).setTitle("§8» §aWłącz");
        ItemBuilder ChatClear = (new ItemBuilder(Material.ENCHANTED_BOOK, 1)).setTitle("§8» §7Wyczyść");

        inv.setItem(3, ChatOff.build());
        inv.setItem(4, ChatOn.build());
        inv.setItem(5, ChatClear.build());

        return p.openInventory(inv);
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if ("§8» §cZarządzanie czatem".equalsIgnoreCase(e.getView().getTitle())) {
            e.setCancelled(true);

            if (e.getSlot() == 3) {
                chatDisabled = true;
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8» §7Chat został §cwyłączony§7!");
                Bukkit.broadcastMessage("§8» §7Przez administratora: §c" + p.getDisplayName());
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                p.sendMessage("§8» §cWyłączyłeś czat!");

            }
            if (e.getSlot() == 4) {
                chatDisabled = false;
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8» §7Chat został §awłączony§7!");
                Bukkit.broadcastMessage("§8» §7Przez administratora: §c" + p.getDisplayName());
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                p.sendMessage("§8» §aWłączyłeś czat!");
            }
            if (e.getSlot() == 5) {
                for (int i = 0; i < 100; i++) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(" ");
                    }
                }
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8» §7Chat został wyczyszczony!");
                Bukkit.broadcastMessage("§8» §7Przez administratora: §c" + p.getDisplayName());
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
                Bukkit.broadcastMessage(" ");
                p.sendMessage("§8» §cWyczyściłeś czat!");
            }
        }
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
        if (chatDisabled && (!e.getPlayer().hasPermission("lightblock.chatmanage") || !e.getPlayer().isOp())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(" ");
            e.getPlayer().sendMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
            e.getPlayer().sendMessage(" ");
            e.getPlayer().sendMessage("§8» §7Chat jest aktualnie wyłączony!");
            e.getPlayer().sendMessage(" ");
            e.getPlayer().sendMessage("§8★☆★☆★☆★☆★☆★☆ §eCHAT §8★☆★☆★☆★☆★☆★☆");
            e.getPlayer().sendMessage(" ");
        }
    }
}