package org.aman.epiclifesteal.utils;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.entity.Player;

public class HeartUtils {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
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
        return (int) (player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue()/2);
    }
}
