package org.aman.epiclifesteal.database;

import org.aman.epiclifesteal.EpicLifesteal;

import java.sql.Connection;

public abstract class DO {
    EpicLifesteal plugin;
    Connection connection;
    public DO(EpicLifesteal instance) {
        this.plugin = instance;
    }


    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        this.connection = this.getSQLConnection();
    }
}
