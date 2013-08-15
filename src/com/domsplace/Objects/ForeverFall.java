package com.domsplace.Objects;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;

public class ForeverFall {
    public static final List<ForeverFall> falls = new ArrayList<ForeverFall>();
    
    /*** Instance ***/
    private World from;
    private World to;
    private double fint;
    private double tint;
    private String msg;
    
    public double forceX;
    public double forceZ;
    
    public boolean shouldForceX = false;
    public boolean shouldForceZ = false;
    
    public ForeverFall(World fromWorld, World toWorld, double fromY, double toY, String message) {
        this.from = fromWorld;
        this.to = toWorld;
        this.fint = fromY;
        this.tint = toY;
        this.msg = message;
    }
    
    public World getFrom() {
        return this.from;
    }
    
    public World getTo() {
        return this.to;
    }
    
    public double getFromY() {
        return this.fint;
    }
    
    public double getToY() {
        return this.tint;
    }
    
    public String getMessage() {
        return this.msg;
    }

    public Location getToAsLocation(Location loc) {
        Location location =  new Location(getTo(), loc.getX(), getToY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        
        if(this.shouldForceX) {
            location.setX(this.forceX);
        }
        
        if(this.shouldForceZ) {
            location.setZ(this.forceZ);
        }
        
        return location;
    }
}
