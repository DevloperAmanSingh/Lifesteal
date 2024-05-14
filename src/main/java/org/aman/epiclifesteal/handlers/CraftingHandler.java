package org.aman.epiclifesteal.handlers;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class CraftingHandler {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();

    public void registerRecipes() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("FireLifesteal");
        ShapedRecipe heartRecipe = (new ShapedRecipe(new NamespacedKey((Plugin) EpicLifesteal.getPlugin(EpicLifesteal.class), "player-heart"), (new Heart()).giveHeart(1))).shape(new String[] { "123", "456", "789" });
        heartRecipe.setIngredient('1', Material.valueOf(this.plugin.configData.getString("crafting.1")));
        heartRecipe.setIngredient('2', Material.valueOf(this.plugin.configData.getString("crafting.2")));
        heartRecipe.setIngredient('3', Material.valueOf(this.plugin.configData.getString("crafting.3")));
        heartRecipe.setIngredient('4', Material.valueOf(this.plugin.configData.getString("crafting.4")));
        heartRecipe.setIngredient('5', Material.valueOf(this.plugin.configData.getString("crafting.5")));
        heartRecipe.setIngredient('6', Material.valueOf(this.plugin.configData.getString("crafting.6")));
        heartRecipe.setIngredient('7', Material.valueOf(this.plugin.configData.getString("crafting.7")));
        heartRecipe.setIngredient('8', Material.valueOf(this.plugin.configData.getString("crafting.8")));
        heartRecipe.setIngredient('9', Material.valueOf(this.plugin.configData.getString("crafting.9")));
        Bukkit.addRecipe((Recipe) heartRecipe);
        Bukkit.getLogger().info("CraftingRecipes has been loaded..");
    }
}
