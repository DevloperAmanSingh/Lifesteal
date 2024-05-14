package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class SetHeart {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els setheart")
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
        if (this.plugin.data.doesPlayerExist(target.getUniqueId().toString())) {
            Player targets = this.plugin.getServer().getPlayer(target.getUniqueId());
            if(Integer.parseInt(heartAmount) > plugin.data.getPlayerMaxHearts(targets.getUniqueId().toString()) ){
                executor.getSender().sendMessage(this.plugin.utils.translateHexCode("&cError: &7 Heart cap of player is "+plugin.data.getPlayerMaxHearts(targets.getUniqueId().toString())));
                return;
            }
            targets.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Integer.parseInt(heartAmount) * 2);
            targets.setHealth(Integer.parseInt(heartAmount) * 2);
            executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsSet(targets.getName(),amount));
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}

