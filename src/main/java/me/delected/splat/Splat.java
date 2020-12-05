package me.delected.splat;

import me.delected.splat.commands.Gamemode;
import me.delected.splat.listeners.PaintSplattered;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;
import java.io.IOException;

public final class Splat extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PaintSplattered(), this);
        Bukkit.getPluginCommand("gm").setExecutor(new Gamemode());
        saveDefaultConfig();
        setupGunConfig();


    }
    public void setupGunConfig() {
        try {
            GunConfig.setup();
            GunConfig.get().options().copyHeader(true).header("For a tutorial and template, read the github wiki!");
            GunConfig.get().addDefault("guns", "splatter_gun");
            GunConfig.get().addDefault("guns.splatter_gun", "rarity");
            GunConfig.get().addDefault("guns.splatter_gun.rarity", "basic");
            GunConfig.get().options().copyDefaults(true);
            GunConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
