package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

import java.util.List;

public class PlayerEliminator {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els eliminate")
    public void setMax(BukkitCommandActor executor , Player target ) {
        if (!executor.getSender().hasPermission("els.admin")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
            return;
        }
        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            OfflinePlayer player = this.plugin.getServer().getOfflinePlayer(target.getUniqueId());
            long currentTime = System.currentTimeMillis();
            this.plugin.data.setPlayerBanTime(player.getUniqueId().toString(), currentTime);
            this.plugin.data.setPlayerEliminated(player.getUniqueId().toString(), true);
            if(target.isOnline()){
                target.kickPlayer(this.plugin.utils.translateHexCode(this.plugin.configData.getString("bans.kick-message")));
            }
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
