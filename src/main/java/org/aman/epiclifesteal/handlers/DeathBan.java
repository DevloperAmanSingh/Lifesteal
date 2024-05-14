package org.aman.epiclifesteal.handlers;

import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class DeathBan {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    public void handleDeathBan(Player player, long currentTime) {
        String UUID = player.getUniqueId().toString();
        player.getInventory().clear();
        long ctime = System.currentTimeMillis();
        this.plugin.data.setPlayerBanTime(player.getUniqueId().toString(), ctime);
        this.plugin.data.setPlayerEliminated(player.getUniqueId().toString(), true);
        player.kickPlayer(this.plugin.utils.translateHexCode(this.plugin.configData.getString("bans.kick-message")));
    }

}
