package org.aman.epiclifesteal.database;

import org.aman.epiclifesteal.EpicLifesteal;
import org.aman.epiclifesteal.utils.Threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public abstract class DatabaseOperation {
    EpicLifesteal plugin;
    Connection connection;
    public DatabaseOperation(EpicLifesteal instance) {
        this.plugin = instance;
    }


    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        this.connection = this.getSQLConnection();
    }

    public void createPlayer(String uuid, String name, String ip, int hearts, int max_hearts, boolean eliminated, long ban_time) {
        Threads.executeDataInternal(() -> {
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO players_info (player_uuid, player_name, ip_address, hearts, max_hearts, eliminated, ban_time) VALUES (?, ?, ?, ?, ?, ?, ?)")
            ) {
                preparedStatement.setString(1, uuid);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, ip);
                preparedStatement.setInt(4, hearts);
                preparedStatement.setInt(5, max_hearts);
                preparedStatement.setBoolean(6, eliminated);
                preparedStatement.setLong(7, ban_time);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void setPlayerMaxHearts(String uuid, int newMaxHearts) {
        Threads.executeDataInternal(() -> {
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE players_info SET max_hearts = ? WHERE player_uuid = ?")
            ) {
                preparedStatement.setInt(1, newMaxHearts);
                preparedStatement.setString(2, uuid);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int getPlayerMaxHearts(String uuid) {
        return Threads.getDataInternal(() -> {
            int maxHearts = 0;
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT max_hearts FROM players_info WHERE player_uuid = ?")
            ) {
                preparedStatement.setString(1, uuid);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    maxHearts = resultSet.getInt("max_hearts");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return maxHearts;
        });
    }

    public void setPlayerEliminated(String uuid, boolean isEliminated) {
        Threads.executeDataInternal(() -> {
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE players_info SET eliminated = ? WHERE player_uuid = ?")
            ) {
                preparedStatement.setBoolean(1, isEliminated);
                preparedStatement.setString(2, uuid);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public boolean isPlayerEliminated(String uuid) {

        return Threads.getDataInternal(() -> {
        boolean isEliminated = false;
        try (Connection connection = getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT eliminated FROM players_info WHERE player_uuid = ?")
        ) {
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isEliminated = resultSet.getBoolean("eliminated");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isEliminated;
        });
    }


    public long getPlayerBanTime(String uuid) {
        return Threads.getDataInternal(() -> {
            long banTime = 0;
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT ban_time FROM players_info WHERE player_uuid = ?")
            ) {
                preparedStatement.setString(1, uuid);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    banTime = resultSet.getLong("ban_time");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return banTime;
        });
    }

    public void setPlayerBanTime(String uuid, long newBanTime) {
        Threads.executeDataInternal(() -> {
            try (Connection connection = getSQLConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE players_info SET ban_time = ? WHERE player_uuid = ?")
            ) {
                preparedStatement.setLong(1, newBanTime);
                preparedStatement.setString(2, uuid);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean doesPlayerExist(String uuid) {
        return Threads.getDataInternal(() -> {
        boolean exists = false;
        try (Connection connection = getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) AS count FROM players_info WHERE player_uuid = ?")
        ) {
            preparedStatement.setString(1, uuid);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    });
    }




}
