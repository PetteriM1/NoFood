package nofood;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerEatFoodEvent;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;
import cn.nukkit.plugin.PluginBase;

public class NoFood extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }

    @EventHandler
    public void onFood(PlayerEatFoodEvent e) {
        if (this.getConfig().getStringList("Worlds").contains(e.getPlayer().getLevel().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(PlayerFoodLevelChangeEvent e) {
        if (this.getConfig().getStringList("Worlds").contains(e.getPlayer().getLevel().getName())) {
            e.setCancelled(true);
        }
    }
}
