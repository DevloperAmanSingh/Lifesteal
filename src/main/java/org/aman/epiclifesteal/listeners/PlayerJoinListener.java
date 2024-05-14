package org.aman.epiclifesteal.listeners;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.database.DatabaseOperation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.logging.Level;

public class PlayerJoinListener implements Listener{
    private EpicLifesteal plugin = EpicLifesteal.getInstance();
    private final DatabaseOperation databaseOperation;

    public PlayerJoinListener(DatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }


    @EventHandler
    public void onPlayerLoginEvent(AsyncPlayerPreLoginEvent event){
        String identifier = event.getUniqueId().toString();
//        Bukkit.getLogger().log(Level.SEVERE,"here is the daata->"+this.plugin.data.isPlayerBannedAsync(identifier));
        if(this.plugin.data.isPlayerEliminated(identifier)){
            long currentTime = System.currentTimeMillis();
            long getBanTime = databaseOperation.getPlayerBanTime(identifier);
            long timeSinceBan = currentTime - getBanTime;
            long BAN_DURATION = this.plugin.configData.getLong("bans.defaultBanTime") * 1000;
            System.out.println(timeSinceBan);
            if (timeSinceBan >= BAN_DURATION ) {
                databaseOperation.setPlayerBanTime(identifier,0);
                databaseOperation.setPlayerEliminated(identifier,false);
                return;
            } else {
                long remainingSeconds = (BAN_DURATION - timeSinceBan) ;
                List<String> bansMessage = EpicLifesteal.getInstance().getConfig().getStringList("bans.Message");
                StringBuilder displayMessage = new StringBuilder();
                for (String message : bansMessage) {
                    Bukkit.getLogger().info(message);
                    message = message.replace("%duration%", this.plugin.utils.formatTime(remainingSeconds));
                    displayMessage.append(message).append("\n");
                }
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED , this.plugin.utils.translateHexCode
                        (this.plugin.configData.getString("bans.Message").toString()));
            }
        }

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!databaseOperation.doesPlayerExist(player.getUniqueId().toString())){
           this.plugin.data.createPlayer(
                   player.getUniqueId().toString(),
                   player.getName(),
                   player.getAddress().getHostString(),
                   10,
                    this.plugin.configData.getInt("heartCap.max"),
                   false,
                   0
           );
        }
        // if player has hearts greater than max hearts , set thier heart to max hearts
//        if (this.plugin.heartUtils.getMaxHearts(player) > this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString())) {
//            this.plugin.heartUtils.setMaxHearts(player, this.plugin.data.getPlayerMaxHearts(player.getUniqueId().toString()));
//        }
    }
}
