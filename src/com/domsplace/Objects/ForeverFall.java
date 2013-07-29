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
    private int fint;
    private int tint;
    private String msg;
    
    public ForeverFall(World fromWorld, World toWorld, int fromY, int toY, String message) {
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
    
    public int getFromY() {
        return this.fint;
    }
    
    public int getToY() {
        return this.tint;
    }
    
    public String getMessage() {
        return this.msg;
    }

    public Location getToAsLocation(Location loc) {
        return new Location(getTo(), loc.getX(), getToY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }
}
