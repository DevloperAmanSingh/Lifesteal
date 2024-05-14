package org.aman.epiclifesteal.manager;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Expansion extends PlaceholderExpansion{
    private final EpicLifesteal plugin;
    public Expansion(EpicLifesteal plugin){
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "els";
    }

    @Override
    public @NotNull String getAuthor() {
        return "EpicAman";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.4";
    }
    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }


    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.endsWith("heartcap")) {
            return String.valueOf(this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString()));
        }
        if (params.endsWith("hearts")) {
//            return String.valueOf(this.plugin.databaseManagerHandler.getShards(player.getUniqueId().toString()));
            return String.valueOf(this.plugin.heartUtils.getMaxHearts(player));
        }else{
            return "0";
        }
    }
}
