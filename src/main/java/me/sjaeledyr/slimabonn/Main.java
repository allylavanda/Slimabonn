package me.sjaeledyr.slimabonn;

import me.sjaeledyr.slimabonn.util.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Slimabonn by Sjaeledyr has been loaded!");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Slimabonn b Sjaeledyr has been unloaded!");
    }
}
