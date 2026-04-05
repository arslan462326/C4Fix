package ru.yourname.c4fix;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() { getServer().getPluginManager().registerEvents(this, this); }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplode(EntityExplodeEvent e) {
        if (!(e.getEntity() instanceof TNTPrimed)) return;
        String name = e.getEntity().getCustomName();
        if (name != null && name.toLowerCase().contains("c4")) {
            int r = 4;
            for (int x = -r; x <= r; x++) {
                for (int y = -r; y <= r; y++) {
                    for (int z = -r; z <= r; z++) {
                        Block b = e.getLocation().clone().add(x, y, z).getBlock();
                        if (b.getType() == Material.OBSIDIAN) b.breakNaturally();
                    }
                }
            }
        }
    }
}
