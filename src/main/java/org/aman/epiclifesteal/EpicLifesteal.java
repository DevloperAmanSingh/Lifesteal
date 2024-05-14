package org.aman.epiclifesteal;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.aman.epiclifesteal.commands.ReloadCommand;
import org.aman.epiclifesteal.database.DatabaseConnector;
import org.aman.epiclifesteal.database.DatabaseOperation;
import org.aman.epiclifesteal.handlers.*;
import org.aman.epiclifesteal.listeners.HeartClaimListener;
import org.aman.epiclifesteal.listeners.PlayerDeathListener;
import org.aman.epiclifesteal.manager.Expansion;
import org.aman.epiclifesteal.utils.Eutils;
import org.aman.epiclifesteal.utils.HeartUtils;
import org.aman.epiclifesteal.utils.Threads;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import java.io.File;
import java.io.IOException;

public final class EpicLifesteal extends JavaPlugin {
    public YamlDocument configData;
//    public YamlDocument itemsData;
    public YamlDocument messsagesData;
    public static EpicLifesteal getInstance(){
        return instance;
    }
    public static EpicLifesteal instance;
    public static Heart heartItem ;
    public static DropHearts dropHearts;
    public  MessageHandler messageHandler;
    public static HeartUtils heartUtils;
    public static DeathBan deathBan;
    public DatabaseOperation data;
    public Eutils utils = new Eutils();


    @Override
    public void onEnable() {
        instance = this;
        try {
            this.configData =  YamlDocument.create(new File("plugins/EpicLifesteal", "config.yml"), this.getClass().getClassLoader().getResource("config.yml").openStream(), GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
            this.messsagesData =  YamlDocument.create(new File("plugins/EpicLifesteal", "message.yml"), this.getClass().getClassLoader().getResource("message.yml").openStream(), GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());

//            this.itemsData =  YamlDocumentument.create(new File("plugins/EpicLifesteal", "items.yml"), this.getClass().getClassLoader().getResource("items.yml").openStream(), GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
            this.data = new DatabaseConnector(this);
            this.data.load();
        FileConfiguration config = this.getConfig();
//        config.options().copyDefaults(true);
        this.saveDefaultConfig();
        Threads.load();
        new Expansion(this).register();
        heartItem = new Heart();
        dropHearts = new DropHearts();
        heartUtils = new HeartUtils();
        deathBan = new DeathBan();
        messageHandler = new MessageHandler();
        if(this.configData.getBoolean("crafting.enabled")){
            CraftingHandler craftingManager = new CraftingHandler();
           craftingManager.registerRecipes();
        }
        registerCommands();
        registerEvents();
    }

    private void registerCommands(){
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);
        handler.register(new ReloadCommand());
        handler.register(new org.aman.epiclifesteal.commands.HeartSetMax());
        handler.register(new org.aman.epiclifesteal.commands.PlayerAddHearts());
        handler.register(new org.aman.epiclifesteal.commands.PlayerEliminator());
        handler.register(new org.aman.epiclifesteal.commands.PlayerReviver());
        handler.register(new org.aman.epiclifesteal.commands.GiveHearts());
        handler.register(new org.aman.epiclifesteal.commands.RemoveHearts());
        handler.register(new org.aman.epiclifesteal.commands.SetHeart());
        handler.register(new org.aman.epiclifesteal.commands.WithdrawHearts());
        handler.registerBrigadier();
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new org.aman.epiclifesteal.listeners.PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new org.aman.epiclifesteal.listeners.PlayerJoinListener(data), this);
        Bukkit.getPluginManager().registerEvents(new HeartClaimListener(data),this);
    }

    public void onDisable() {
        Threads.close();
    }



}
