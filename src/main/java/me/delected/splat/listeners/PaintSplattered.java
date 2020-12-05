package me.delected.splat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.List;

public class PaintSplattered implements Listener {
    @EventHandler
    public void onPaintSplat(ProjectileHitEvent e) {
        if (e.getEntity().getType() != EntityType.SNOWBALL) return;
        if (!(e.getEntity().getShooter() instanceof Player)) return;
        BlockIterator iterator = new BlockIterator(e.getEntity().getWorld(), e.getEntity().getLocation().toVector(), e.getEntity().getVelocity().normalize(), 0.0D, 4);
        Block hitBlock = null;

        while (iterator.hasNext()) {
            hitBlock = iterator.next();

            if (hitBlock.getType() != Material.AIR) {
                break;
            }
        }
        int radius = 3;
        assert hitBlock != null;
        if (hitBlock.getType() == Material.WOOL || hitBlock.getType() == Material.STAINED_GLASS) {
            for (int i = 0; i < getBlocksAroundCenter(hitBlock.getLocation(), radius).size(); i++) {
                if (getBlocksAroundCenter(hitBlock.getLocation(), radius).get(i).getType() == Material.WOOL || getBlocksAroundCenter(hitBlock.getLocation(), radius).get(i).getType() == Material.STAINED_GLASS) {
                    getBlocksAroundCenter(hitBlock.getLocation(), radius).get(i).setTypeIdAndData(35, (byte) 14, true); // let's pretend this isn't deprecated
                }
            }
        }
    }
    public static int getRadius(String rarity) {
        if (rarity.equalsIgnoreCase(ChatColor.GRAY + "" + ChatColor.BOLD + "Basic Gun")) return 2;
        if (rarity.equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Uncommon Gun")) return 3;
        if (rarity.equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Gun")) return 4;
        if (rarity.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Very Rare Gun")) return 5;
        return 2;
    }
    public static ArrayList<Block> getBlocksAroundCenter(Location loc, int radius) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int x = (loc.getBlockX()-radius); x <= (loc.getBlockX()+radius); x++) {
            for (int y = (loc.getBlockY()-radius); y <= (loc.getBlockY()+radius); y++) {
                for (int z = (loc.getBlockZ()-radius); z <= (loc.getBlockZ()+radius); z++) {
                    Location l = new Location(loc.getWorld(), x, y, z);
                    if (l.distance(loc) <= radius) {
                        blocks.add(l.getBlock());
                    }
                }
            }
        }
        return blocks;
    }
}

