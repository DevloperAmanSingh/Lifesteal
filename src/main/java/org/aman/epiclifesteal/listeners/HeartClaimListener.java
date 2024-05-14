package org.aman.epiclifesteal.listeners;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.database.DatabaseOperation;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.xml.crypto.Data;

public class HeartClaimListener implements Listener {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    private DatabaseOperation databaseOperation;

    public HeartClaimListener(DatabaseOperation databaseOperation){
        this.databaseOperation = databaseOperation;
    }
    @EventHandler
    public void onRightClickHeart(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item == null || !item.hasItemMeta()) {
                return;
            }
            if (!this.checkItem(item)) {
                return;
            }
            if (!player.hasPermission("els.deposit")) {
                player.sendMessage((String) this.plugin.messageHandler.insufficientPermission());
                return;
            }
            int heartCap = databaseOperation.getPlayerMaxHearts(player.getUniqueId().toString());
            if (plugin.heartUtils.getMaxHearts(player) <= heartCap) {
                item.setAmount(item.getAmount() - 1);
                this.plugin.heartUtils.addHeart(player, 1);
                player.sendMessage((String) this.plugin.messageHandler.heartsRedeemed());
            } else {
                player.sendMessage((String) this.plugin.messageHandler.capReached(player.getName()));
            }
        }

    }
    private boolean checkItem(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(EpicLifesteal.getInstance(), "player-heart"), PersistentDataType.BOOLEAN);
    }   
}
