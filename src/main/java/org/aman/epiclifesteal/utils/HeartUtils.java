package org.aman.epiclifesteal.handlers;

import org.bukkit.entity.Player;

public class HeartHandler {
    public void addHeart(Player player , int amount){
        player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() + (amount*2));
    }
    public void removeHeart(Player player , int amount){
        player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (amount*2) );
    }
    public void setMaxHearts(Player player, int hearts){
        player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(hearts*2);
    }
    public int getMaxHearts(Player player){
        int maxHearts = (int) (player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue()/2);
        return maxHearts;
    }
}
