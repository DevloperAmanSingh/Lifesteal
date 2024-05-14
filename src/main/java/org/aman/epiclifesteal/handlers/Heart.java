package org.aman.epiclifesteal.handlers;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Heart {

    private EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);
//    public ItemStack getHeart(String heartId) {
//        ItemStack heart = new ItemStack(Material.valueOf(this.plugin.itemsData.getString("hearts." + heartId + ".material")));
//        ItemMeta heartMeta = heart.getItemMeta();
//        heartMeta.setDisplayName(this.plugin.itemsData.getString("hearts." + heartId + ".name"));
//        List<String> lore = new ArrayList<>();
//        for (String line : this.plugin.itemsData.getStringList("hearts." + heartId + ".lore")) {
//            lore.add(line);
//        }
//        heartMeta.setLore(lore);
//        heartMeta.getPersistentDataContainer().set(new NamespacedKey((Plugin) this.plugin, "heartId"), PersistentDataType.STRING, heartId);
//        heart.setItemMeta(heartMeta);
//        return heart;
//    }

    private final Material material = Material.getMaterial(this.plugin.configData.getString("heart-item.material"));
    private final String name = this.plugin.configData.getString("heart-item.name");
    private final int amount = 1;

    public ItemStack giveHeart(int amount) {
        ItemStack heart = new ItemStack(this.material,amount);
        ItemMeta heartMeta = heart.getItemMeta();
        heartMeta.setDisplayName(this.plugin.utils.translateHexCode(name));
        List<String> newLore = new ArrayList<>();
        for (String line : this.plugin.configData.getStringList("heart-item.lore")) {
            newLore.add(this.plugin.utils.translateHexCode(line));
        }
        heartMeta.setLore(newLore);
        heartMeta.getPersistentDataContainer().set(new NamespacedKey( (Plugin) EpicLifesteal.getPlugin(EpicLifesteal.class), "player-heart"), PersistentDataType.BOOLEAN, Boolean.valueOf(true));
        heart.setItemMeta(heartMeta);
        return heart;
    }
}
