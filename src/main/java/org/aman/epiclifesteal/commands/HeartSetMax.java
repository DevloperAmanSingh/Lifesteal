package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.OfflinePlayer;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class HeartSetMax {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els setmax")
    public void setMax(BukkitCommandActor executor , OfflinePlayer target , String heartAmount ) {
        if (!executor.getSender().hasPermission("els.admin")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.insufficientPermission());
            return;
        }
        if (!this.plugin.utils.isInt(String.valueOf(heartAmount))) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidAmount());
            return;
        }
        int amount = Integer.parseInt(heartAmount);
        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            OfflinePlayer player = this.plugin.getServer().getOfflinePlayer(target.getUniqueId());
            this.plugin.data.setPlayerMaxHearts(player.getUniqueId().toString(), amount);

            executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsSetMax(player.getName(),amount));
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
