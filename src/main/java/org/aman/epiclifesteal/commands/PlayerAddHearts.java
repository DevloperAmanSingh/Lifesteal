package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class PlayerAddHearts  {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els addHearts")
    public void setMax(BukkitCommandActor executor , Player target , String heartAmount ) {
        if (!executor.getSender().hasPermission("els.admin")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.insufficientPermission());
            return;
        }
        if (!this.plugin.utils.isInt(String.valueOf(heartAmount))) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidAmount());
            return;
        }
        int amount = Integer.parseInt(heartAmount);

        if ((!Bukkit.getOfflinePlayer(target.getUniqueId()).isOnline())) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
            return;
        }

        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            int heartCap = this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString());
            if (this.plugin.heartUtils.getMaxHearts(player) + amount > heartCap) {
                executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsExceedsMax());
                return;
            }
            executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsAdd(target.getName(),amount));
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
