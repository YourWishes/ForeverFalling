package com.domsplace.ForeverFalling;

import com.domsplace.ForeverFalling.Objects.ForeverFall;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForeverFallingBase {
    
    public static String ChatDefault = ChatColor.GRAY.toString();
    public static String ChatImportant = ChatColor.BLUE.toString();
    public static String ChatError = ChatColor.RED.toString();
    
    public static boolean BlockVoid = true;
    public static boolean BlockFall = true;
    
    public static final List<Player> falls = new ArrayList<Player>();
    
    public static ForeverFallingPlugin getPlugin() {
        return ForeverFallingPlugin.getForeverFallingPlugin();
    }
    
    public static void Error(String reason, Exception cause) {
        Error(reason);
        
        if(cause == null) {
            return;
        }
        
        msgConsole("Show this error to the plugin Admin:");
        cause.printStackTrace();
    }
    
    public static void permBroadcast(String perm, String message) {
        msgConsole(message);
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.hasPermission(perm)) {
                continue;
            }
            
            msgPlayer(p, message);
        }
    }
    
    public static void broadcast(String message) {
        msgConsole(message);
        for(Player p : Bukkit.getOnlinePlayers()) {
            msgPlayer(p, message);
        }
    }
    
    public static void msgConsole(String message) {
        msgPlayer(Bukkit.getConsoleSender(), message);
    }
    
    public static void msgPlayer(CommandSender player, String message) {
        if(message.equalsIgnoreCase("")) return;
        player.sendMessage(ChatDefault + message);
    }
    
    public static void Error(String reason) {
        msgConsole(ChatError + reason);
    }
    
    public static String Color(String string) {
        
        String[] normvalues = { "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f", "&l", "&m", "&n", "&k", "&r", "&o" };
        String[] coloredvalues = { "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f", "§l", "§m", "§n", "§k", "§r", "§o" };
        
        for(int i = 0; i < normvalues.length; i++) {
            string = string.replaceAll(normvalues[i], coloredvalues[i]);
        }
        
        return string;
    }
    
    public static void debug(String message) {
        broadcast("§bDEBUG: §d: " + message);
    }
    
    public static ForeverFall getFall(World world) {
        for(ForeverFall ff : ForeverFall.falls) {
            if(ff.getFrom().equals(world)) {
                return ff;
            }
        }
        
        return null;
    }
}
