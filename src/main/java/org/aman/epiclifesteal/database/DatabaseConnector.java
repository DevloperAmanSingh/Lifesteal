package org.aman.epiclifesteal.database;

import org.aman.epiclifesteal.EpicLifesteal;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DC extends DO{

    private final String dbname = "players";
    private final String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS players_info (player_uuid VARCHAR(36) NOT NULL PRIMARY KEY, player_name VARCHAR(255) NOT NULL, ip_address VARCHAR(45) NOT NULL, hearts INT NOT NULL, max_hearts INT NOT NULL, eliminated BOOLEAN NOT NULL, ban_time BIGINT NOT NULL)";
    public EpicLifesteal instance;
    public DC(EpicLifesteal instance){
        super(instance);
    }

    @Override
    public Connection getSQLConnection() {
        File dataFolder = new File(this.plugin.getDataFolder(), this.dbname + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            }
            catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "File write error: " + this.dbname + ".db");
            }
        }
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                return this.connection;
            }
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return this.connection;
        }
        catch (SQLException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        }
        catch (ClassNotFoundException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    @Override
    public void load() {
        this.connection = this.getSQLConnection();
        try {
            Statement s = this.connection.createStatement();
            s.executeUpdate(this.SQLiteCreateTokensTable);
            s.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        this.initialize();
    }



}
