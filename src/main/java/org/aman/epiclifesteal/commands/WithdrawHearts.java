package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.handlers.Heart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.io.IOException;

public class WithdrawHearts {
    private EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);
    @Command("els withdraw ")
    @CommandPermission("els.withdraw")
    @Description("Withdraw hearts from a player")
    public void withdraw(BukkitCommandActor executor,Player sender, String amounts) {
        if (!executor.getSender().hasPermission("els.withdraw")) {
            executor.getSender().sendMessage((String) this.plugin.messageHandler.insufficientPermission());
            return;
        }
        if (!this.plugin.utils.isInt(String.valueOf(amounts))) {
            sender.sendMessage((String) this.plugin.messageHandler.invalidAmount());
            return;
        }
        int amount = Integer.parseInt(amounts);
        if (this.plugin.data.doesPlayerExist(sender.getUniqueId().toString())) {
            long currentTime = System.currentTimeMillis();
            Inventory inv = sender.getInventory();
            int senderhearts = this.plugin.heartUtils.getMaxHearts(sender);
            if(senderhearts == 1){
                sender.sendMessage("You have only 1 heart left, you can't withdraw it");
                return;
            }
            if (amount > senderhearts) {
                sender.sendMessage((String) this.plugin.messageHandler.insufficentHearts());
                return;
            }
            if (amount == senderhearts) {
                sender.sendMessage("You can't withdraw all your hearts, you need to have at least 1 heart");
                return;
            }
            if (inv.firstEmpty() != -1 ) {
                inv.addItem(new Heart().giveHeart(amount));
                this.plugin.heartUtils.removeHeart(sender,amount);
            } else {
                sender.sendMessage((String) this.plugin.messageHandler.playerInvFull(sender.getName()));
            }
        } else {
            sender.sendMessage((String) this.plugin.messageHandler.invalidPlayer());
        }
    }
}
