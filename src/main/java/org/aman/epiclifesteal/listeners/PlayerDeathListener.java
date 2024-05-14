package org.aman.epiclifesteal.listeners;


import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.handlers.DeathBan;
import org.aman.epiclifesteal.handlers.Heart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    private final EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        boolean naturalHeartDeath = this.plugin.configData.getBoolean("hearts-drop.onNaturalDeath");
        boolean playerKillDrop = this.plugin.configData.getBoolean("hearts-drop.onPlayerKill");
        if (matchIpAddress(player,player.getKiller())) return;

        if (naturalHeartDeath) {
            if (this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString()) <= 0.0D) {
                this.plugin.heartUtils.setMaxHearts(player, this.plugin.configData.getInt("heartCap.afterUnban"));
                long currentTime = System.currentTimeMillis();
                this.plugin.deathBan.handleDeathBan(player, currentTime);
                return;
            }
            EpicLifesteal.dropHearts.dropheartsNaturally(player.getLocation(),player);
            return;
        }
        if (playerKillDrop) {
                Player victim = event.getEntity().getPlayer();
                Player killer = player.getKiller();
                if (killer == null) return;
                if (!(killer instanceof Player)) return;

                EpicLifesteal.dropHearts.dropHearts(victim.getLocation(), victim,killer);
                if (this.plugin.heartUtils.getMaxHearts(player) <= 0.0D) {
                    this.plugin.heartUtils.setMaxHearts(victim, this.plugin.configData.getInt("heartCap.afterUnban"));
                    long currentTime = System.currentTimeMillis();
                    this.plugin.deathBan.handleDeathBan(victim, currentTime);
                    return;
                }
        }
    }
    private boolean matchIpAddress(Player player, Player killer) {
        if (player == null || killer == null) return false;
        return player.getAddress().getAddress().equals(killer.getAddress().getAddress());
    }
}
