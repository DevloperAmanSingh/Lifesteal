package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class RemoveHearts {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els removeHeart")
    public void setMax(BukkitCommandActor executor , OfflinePlayer target , String amounts ) {
        if (!executor.getSender().hasPermission("els.admin")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.insufficientPermission());
            return;
        }
        if (!this.plugin.utils.isInt(String.valueOf(amounts))) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidAmount());
            return;
        }
        int amount = Integer.parseInt(amounts);
        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            long currentTime = System.currentTimeMillis();
            this.plugin.heartUtils.removeHeart(player,amount);
            executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsRemoved(player.getName(),amount));
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
