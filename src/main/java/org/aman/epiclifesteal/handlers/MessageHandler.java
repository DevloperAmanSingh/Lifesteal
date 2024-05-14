package org.aman.epiclifesteal.handlers;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.utils.Eutils;

public class MessageHandler {
    private EpicLifesteal plugin = EpicLifesteal.getInstance();

    // Other methods and fields...

    public <T> T insufficientPermission() {
        return (T) Eutils.translateHexCode(this.plugin.messsagesData.getString("messages.insufficient-permission"));
    }

    public <T> T invalidPlayer() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.invalid-player"));
    }

    public <T> T invalidAmount() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.invalid-amount"));
    }

    public <T> T invalidArgs() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.invalid-args"));
    }

    public <T> T notBanned() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.not-banned"));
    }

    public <T> T heartsExceedsMax() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.hearts-exceeds-max"));
    }

    public <T> T capReached(String player) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.cap-reached")
        );
    }

    public <T> T playerInvFull(String player) {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.player-inv-full")
                .replace("%player%", player)
        );
    }


    public <T> T heartsRedeemed() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.hearts-redeemed"));
    }
    public <T> T insufficentHearts() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.insufficient-hearts"));
    }
    public <T> T heartsGiven(String player, int amount) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.hearts-given")
                        .replace("%player%", player)
                        .replace("%amount%", String.valueOf(amount))
        );
    }

    public <T> T heartsRemoved(String player, int amount) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.hearts-removed")
                        .replace("%player%", player)
                        .replace("%amount%", String.valueOf(amount))
        );
    }


    public <T> T heartsSet(String player, int amount) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.hearts-set")
                        .replace("%player%", player)
                        .replace("%amount%", String.valueOf(amount))
        );
    }

    public <T> T heartsAdd(String player, int amount) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.hearts-add")
                        .replace("%player%", player)
                        .replace("%amount%", String.valueOf(amount))
        );
    }

    public <T> T reloaded() {
        return (T) Eutils.translateHexCode(this.plugin.messsagesData.getString("messages.reloaded"));
    }

    public <T> T unbanned() {
        return (T) this.plugin.utils.translateHexCode(this.plugin.messsagesData.getString("messages.unbanned"));
    }

    public <T> T heartsSetMax(String player, int amount) {
        return (T) this.plugin.utils.translateHexCode(
                this.plugin.messsagesData.getString("messages.hearts-set-max")
                        .replace("%player%", player)
                        .replace("%amount%", String.valueOf(amount))
        );
    }

}
