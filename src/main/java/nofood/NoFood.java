package nofood;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerEatFoodEvent;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;
import cn.nukkit.plugin.PluginBase;

import java.util.HashSet;
import java.util.Set;

public class NoFood extends PluginBase implements Listener {

    private boolean allowEat;
    private Set<String> worlds;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        allowEat = getConfig().getBoolean("AllowEat");
        worlds = new HashSet<>(getConfig().getStringList("Worlds"));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFood(PlayerEatFoodEvent e) {
        if (allowEat) {
            return;
        }
        if (!worlds.contains(e.getPlayer().getLevel().getName())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFood(PlayerFoodLevelChangeEvent e) {
        if (allowEat && e.getFoodLevel() >= e.getPlayer().getFoodData().getLevel() && e.getFoodSaturationLevel() >= e.getPlayer().getFoodData().getFoodSaturationLevel()) {
            return;
        }
        if (!worlds.contains(e.getPlayer().getLevel().getName())) {
            return;
        }
        e.setCancelled(true);
    }
}
