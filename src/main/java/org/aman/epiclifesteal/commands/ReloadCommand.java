package org.aman.epiclifesteal.commands;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.io.IOException;

//import java.io.IOException;
//
public class ReloadCommand {
    private EpicLifesteal plugin = EpicLifesteal.getPlugin(EpicLifesteal.class);
    @Command("els reload")
    @CommandPermission("els.reload")
    @Description("Reload config file")
    public void reload(Player sender) throws IOException {
        this.plugin.configData.reload();
        sender.sendMessage((String) this.plugin.messageHandler.reloaded());
    }
}
