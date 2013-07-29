package com.domsplace.DataManagers;

import com.domsplace.ForeverFallingBase;
import com.domsplace.Objects.ForeverFall;
import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class ForeverFallingFallingManager extends ForeverFallingBase {
    
    public static YamlConfiguration yml;
    
    public static boolean LoadConfig() {
        ForeverFall.falls.clear();
        
        try {
            boolean newConfig = false;
            if(!getPlugin().getDataFolder().exists()) {
                getPlugin().getDataFolder().mkdir();
            }
            
            File configFile = new File(getPlugin().getDataFolder(), "falls.yml");
            if(!configFile.exists()) {
                newConfig = true;
                configFile.createNewFile();
            }
            
            yml = YamlConfiguration.loadConfiguration(configFile);
            
            if(newConfig) {
                yml.set("TestFall.message", "AHHH!");
                yml.set("TestFall.fromWorld", Bukkit.getWorlds().get(0).getName());
                yml.set("TestFall.toWorld", Bukkit.getWorlds().get(1).getName());
                yml.set("TestFall.fromY", -10);
                yml.set("TestFall.toY", 400);
                
                yml.save(configFile);
            }
            
            for(String fall : yml.getKeys(false)) {
                String message = yml.getString(fall + ".message", "AHH!");
                String worldName = yml.getString(fall + ".fromWorld");
                String toWorldName = yml.getString(fall + ".toWorld");
                int fromY = yml.getInt(fall + ".fromY", -10);
                int toY = yml.getInt(fall + ".toY", 400);
                
                World fWorld = Bukkit.getWorld(worldName);
                if(fWorld == null) {
                    msgConsole(ChatError + "Failed to load world " + worldName);
                    continue;
                }
                
                World tWorld = Bukkit.getWorld(toWorldName);
                if(tWorld == null) {
                    msgConsole(ChatError + "Failed to load world " + toWorldName);
                    continue;
                }
                
                ForeverFall fl = new ForeverFall(fWorld, tWorld, fromY, toY, Color(message));
                ForeverFall.falls.add(fl);
            }
            
            return true;
        } catch(Exception ex) {
            Error("Failed to load config", ex);
            return false;
        }
    }
}
