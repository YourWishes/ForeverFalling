package com.domsplace.Commands;

import com.domsplace.DataManagers.ForeverFallingConfigManager;
import com.domsplace.DataManagers.ForeverFallingFallingManager;
import com.domsplace.ForeverFallingBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForeverFallingForeverFallingCommand extends ForeverFallingBase implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("ForeverFalling")) {
            sender.sendMessage(ChatImportant + "Reloading Config..");
            if(!ForeverFallingConfigManager.LoadConfig()) {
                sender.sendMessage(ChatError + "Failed to reload Config!");
                return true;
            }

            if(!ForeverFallingFallingManager.LoadConfig()) {
                sender.sendMessage(ChatError + "Failed to reload Config!");
                return true;
            }
            
            sender.sendMessage(ChatDefault + "Reloaded Config!");
            return true;
        }
        return false;
    }
}
