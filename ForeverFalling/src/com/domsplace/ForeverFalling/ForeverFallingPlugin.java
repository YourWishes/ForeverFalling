package com.domsplace.ForeverFalling;

import com.domsplace.ForeverFalling.Commands.ForeverFallingForeverFallingCommand;
import com.domsplace.ForeverFalling.DataManagers.ForeverFallingConfigManager;
import com.domsplace.ForeverFalling.DataManagers.ForeverFallingFallingManager;
import com.domsplace.ForeverFalling.DataManagers.ForeverFallingPluginManager;
import com.domsplace.ForeverFalling.Listeners.ForeverFallingFallingListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ForeverFallingPlugin extends JavaPlugin {
    
    public static boolean isEnabled = false;
    public static PluginManager pluginManager;
    
    //Define Commands
    public static ForeverFallingForeverFallingCommand ForeverFallingCommand;
    
    //Define Listeners
    public static ForeverFallingFallingListener FallingListener;
    
    @Override
    public void onEnable() {
        pluginManager = Bukkit.getPluginManager();
        
        if(!ForeverFallingPluginManager.LoadPlugin()) {
            Disable();
            return;
        }
        
        if(!ForeverFallingConfigManager.LoadConfig()) {
            Disable();
            return;
        }
        
        if(!ForeverFallingFallingManager.LoadConfig()) {
            Disable();
            return;
        }
        
        //Load Commands
        ForeverFallingCommand = new ForeverFallingForeverFallingCommand();
        
        //Load Listeners
        FallingListener = new ForeverFallingFallingListener();
        
        //Register Commands
        getCommand("ForeverFalling").setExecutor(ForeverFallingCommand);
        
        for(String c : ForeverFallingPluginManager.getCommands()) {
            getCommand(c).setPermission(ForeverFallingPluginManager.PluginYML.getString(ForeverFallingBase.ChatError + "permission"));
        }
        
        //Register Listeners
        pluginManager.registerEvents(FallingListener, this);
        
        
        ForeverFallingBase.permBroadcast(
            "ForeverFalling.*",
            "§dLoaded " + ForeverFallingPluginManager.getName() + 
            " version " + ForeverFallingPluginManager.getVersion() + 
            "!"
        );
        isEnabled = true;
    }
    
    @Override
    public void onDisable() {
        if(!isEnabled) {
            getLogger().info("Failed to load plugin! Check Console for errors.");
            return;
        }
        
        /*** Stop Threads ***/
    }
    
    public void Disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public static ForeverFallingPlugin getForeverFallingPlugin() {
        try {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("ForeverFalling");
            if(plugin == null || !(plugin instanceof ForeverFallingPlugin)) {
                return null;
            }
            
            return (ForeverFallingPlugin) plugin;
        } catch(NoClassDefFoundError e) {
            return null;
        }
    }
    
}
