package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.handlers.Heart;
import org.aman.epiclifesteal.handlers.MessageHandler;
import org.aman.epiclifesteal.utils.HeartUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandActor;

public class GiveHearts {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    @Command("els givehearts")
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
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            ItemStack heartItemStack = new Heart().giveHeart(Integer.valueOf(heartAmount));
            player.getInventory().addItem(heartItemStack);
            executor.getSender().sendMessage((String) this.plugin.messageHandler.heartsGiven(target.getName(),amount));
        } else {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
