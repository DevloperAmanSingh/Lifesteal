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

public class HeartItem {

    private EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);
    public ItemStack getHeart(String heartId) {
        ItemStack heart = new ItemStack(Material.valueOf(this.plugin.itemsData.getString("hearts." + heartId + ".material")));
        ItemMeta heartMeta = heart.getItemMeta();
        heartMeta.setDisplayName(this.plugin.itemsData.getString("hearts." + heartId + ".name"));
        List<String> lore = new ArrayList<>();
        for (String line : this.plugin.itemsData.getStringList("hearts." + heartId + ".lore")) {
            lore.add(line);
        }
        heartMeta.setLore(lore);
        heartMeta.getPersistentDataContainer().set(new NamespacedKey((Plugin) this.plugin, "heartId"), PersistentDataType.STRING, heartId);
        heart.setItemMeta(heartMeta);
        return heart;
    }
}
