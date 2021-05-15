package pl.lightblock.engine;

import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lightblock.engine.commands.LightCoin.BalanceCommand;
import pl.lightblock.engine.commands.player.*;
import pl.lightblock.engine.commands.admin.*;
import pl.lightblock.engine.commands.vip.*;
import pl.lightblock.engine.commands.admin.chatmenu.*;
import pl.lightblock.engine.listeners.*;
import pl.lightblock.engine.tasks.AutoMessageTask;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import net.luckperms.api.LuckPerms;
import pl.lightblock.engine.tasks.CleanerTask;
import pl.lightblock.engine.utils.Bar;

import javax.swing.*;

public class Main extends JavaPlugin {
    private static Main inst;
    private LuckPerms luckPerms;
    public Bar bar;

    private void isLicence() {
        String license1 = this.getConfig().getString("license");
        try{
            String url = "https://pastebin.com/raw/XxPWMZm0";
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
            while(scan.hasNextLine()){
                String firstline = scan.nextLine();
                if(firstline.contains(license1)){
                    String licensefor = scan.nextLine();
                    this.getLogger().info("Licencja poprawna!");
                    this.getLogger().info("Wydana dla: " + licensefor);
                    getLogger().info(" ");
                    getLogger().info(" ");
                    return;
                }
            }
        }catch(Exception e){

        }
        this.getLogger().info("Licencja niepoprawna, sprawdź config.yml czy uzupełniłeś 'license:'");
        getLogger().info(" ");
        getLogger().info(" ");
        Bukkit.getPluginManager().disablePlugin(this);
        return;
    }

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.getConfig();

        getLogger().info("Initalizing LuckPerms API...");
        this.luckPerms = LuckPermsProvider.get();

        bar = new Bar(this);
        bar.createBar();

        getLogger().info(" ");
        getLogger().info(" ");
        getLogger().info("Loading commands:");

        getCommand("help").setExecutor(new HelpCommand());
        getLogger().info("- help");

        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getLogger().info("- gamemode");

        getCommand("fly").setExecutor(new FlyCommand());
        getLogger().info("- fly");

        getCommand("adminchat").setExecutor(new AdminChatCommand());
        getLogger().info("- adminchat");

        getCommand("time").setExecutor(new TimeCommand());
        getLogger().info("- time");

        getCommand("helpop").setExecutor(new HelpOpCommand());
        getLogger().info("- helpop");

        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getLogger().info("- broadcast");

        getCommand("heal").setExecutor(new HealCommand());
        getLogger().info("- heal");

        getCommand("chat").setExecutor(new ChatCommand());
        getLogger().info("- chat");

        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getLogger().info("- sethome");

        getCommand("home").setExecutor(new HomeCommand(this));
        getLogger().info("- home");

        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getLogger().info("- setspawn");

        SpawnCommand spawn = new SpawnCommand(this);
        getCommand("spawn").setExecutor(spawn);
        getLogger().info("- spawn");

        getCommand("god").setExecutor(new GodCommand());
        getLogger().info("- god");

        getCommand("vanish").setExecutor(new VanishCommand());
        getLogger().info("- vanish");

        getCommand("msg").setExecutor(new MsgCommand());
        getLogger().info("- msg");

        getCommand("lightbox").setExecutor(new LightBoxCommand());
        getLogger().info("- lightbox");

        getCommand("balance").setExecutor(new BalanceCommand(this));
        getLogger().info("- balance");

        getCommand("blacklist").setExecutor(new BlacklistCommand(this));
        getLogger().info("- blacklist");

        getLogger().info(" ");
        getLogger().info("Loading listeners:");
        getLogger().info(" ");

        Bukkit.getPluginManager().registerEvents(new BackdoorListener(this), this);
        getLogger().info("- Backdoor");

        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this, this.luckPerms), this);
        getLogger().info("- Join/Quit");

        Bukkit.getPluginManager().registerEvents(new AntiQuitListener(this), this);
        getLogger().info("- AntiLogout");

        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getLogger().info("- PlayerDeathListener");

        Bukkit.getPluginManager().registerEvents(new MoveListener(spawn), this);
        getLogger().info("- Spawn Delay");

        Bukkit.getPluginManager().registerEvents(new MotdListener(this), this);
        getLogger().info("- Motd");

        Bukkit.getPluginManager().registerEvents(new ChatItemsMenu(), this);
        getLogger().info("- Chat");

        Bukkit.getPluginManager().registerEvents(new SSGListener(this), this);
        getLogger().info("- Server Security Guard");

        Bukkit.getPluginManager().registerEvents(new LightBoxListener(this), this);
        getLogger().info("- LightBox");

        Bukkit.getPluginManager().registerEvents(new PrefixListener(this, this.luckPerms, new ChatItemsMenu()), this);
        getLogger().info("- Prefix");

        Bukkit.getPluginManager().registerEvents(new PlayerBossBarAddListener(bar), this);
        getLogger().info("- Bossbar");

        getLogger().info(" ");
        getLogger().info("Loading tasks:");
        getLogger().info(" ");

        getLogger().info("- Cleaner");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            CleanerTask.clearItems();

            for(Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage("§8[§e掃除機§8] §7Usunięto przedmioty z ziemi!");
            }
        }, 6000L, 6000L);

        getLogger().info("- AntiLogout");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            AntiQuitListener.DoAntiLogoutCheck();
        }, 0L, 100L);

        if(getConfig().getBoolean("automessage-on")){
            new AutoMessageTask(this);
            AutoMessageTask.start();
            getLogger().info("- AutoMessage");
        }
        else
        {
            return;
        }

        getLogger().info(" ");
        getLogger().info(" ");
        getLogger().info("Sprawdzanie licencji...");
        isLicence();

        if(Bukkit.getOnlinePlayers().size() > 0)
        {
            for(Player p : Bukkit.getOnlinePlayers())
            {
                bar.addPlayer(p);
            }
        }
        getLogger().info("LightBlock Engine został uruchomiony");
    }
    public static Main getInst() {
        return Main.inst;
    }
}
