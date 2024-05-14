package org.aman.epiclifesteal.handlers;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropHearts {
    private EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);

    public void dropheartsNaturally(Location location , Player player){
        if (this.plugin.configData.getBoolean("settings.dropAsItem")) {
            ItemStack heart = new Heart().giveHeart(this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
            this.plugin.heartUtils.removeHeart(player, this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
            location.getWorld().dropItemNaturally(location, heart);
            return;
        } else {
            addHeartsVirtually(player, this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
        }
    }


    public void dropHearts(Location location , Player Victim,Player player){
        if (this.plugin.configData.getBoolean("settings.dropAsItem")) {
            ItemStack heart = new Heart().giveHeart(this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
            this.plugin.heartUtils.removeHeart(Victim, this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
            location.getWorld().dropItemNaturally(location, heart);
            return;
        } else {
            int heartCap = this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString());
            int heartCapFromConfig =  this.plugin.configData.getInt("heartCap.max");
            if (plugin.heartUtils.getMaxHearts(player) <= heartCap) {
                addHeartsVirtually(player, this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
            }
            removeHeartsVirtually(Victim, this.plugin.configData.getInt("hearts-drop.heartsToDrop"));
        }
    }
    public void removeHeartsVirtually(Player player, int heartsToRemove) {
        this.plugin.heartUtils.removeHeart(player, heartsToRemove);
    }

    public void addHeartsVirtually(Player player, int heartsToAdd) {
        this.plugin.heartUtils.addHeart(player, heartsToAdd);
    }

}
