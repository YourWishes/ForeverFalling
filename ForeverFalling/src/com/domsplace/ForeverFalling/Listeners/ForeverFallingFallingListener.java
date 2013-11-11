package com.domsplace.ForeverFalling.Listeners;

import com.domsplace.ForeverFalling.Objects.ForeverFall;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

public class ForeverFallingFallingListener extends ForeverFallingListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onDamage(EntityDamageEvent e) {
        if(!BlockVoid && !BlockFall) {
            return;
        }
        
        if(e.getEntity() == null || !(e.getEntity() instanceof Player)) {
            return;
        }
        
        Player p = (Player) e.getEntity();
        
        if(BlockVoid && e.getCause().equals(DamageCause.VOID)) {
            e.setDamage(0d);
            e.setCancelled(true);
            return;
        }
        
        if(BlockFall && e.getCause().equals(DamageCause.FALL)) {
            /* TODO: Finish */
            
            if(!falls.contains(p)) {
                return;
            }
            
            falls.remove(p);
            e.setDamage(0d);
            e.setCancelled(true);
            return;
        }
    }
    
    @EventHandler(ignoreCancelled=true)
    public void onFall(PlayerMoveEvent e) {
        ForeverFall fall = getFall(e.getPlayer().getWorld());
        
        if(fall == null) {
            return;
        }
        
        double playerY = e.getTo().getY();
        if(playerY > fall.getFromY()) {
            return;
        }
        
        msgPlayer(e.getPlayer(), fall.getMessage());
        
        e.setTo(fall.getToAsLocation(e.getTo()));
        falls.add(e.getPlayer());
    }
}
