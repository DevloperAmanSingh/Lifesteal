package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class PlayerReviver {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els revive")
    public void setMax(BukkitCommandActor executor , OfflinePlayer target  ) {
        if (!executor.getSender().hasPermission("els.admin")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.insufficientPermission());
            return;
        }
        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            OfflinePlayer player = this.plugin.getServer().getOfflinePlayer(target.getUniqueId());
            long currentTime = System.currentTimeMillis();
            this.plugin.data.setPlayerBanTime(player.getUniqueId().toString(), 0);
            executor.getSender().sendMessage((String) this.plugin.messageHandler.unbanned());
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
