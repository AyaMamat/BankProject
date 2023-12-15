package com.laba.solvd.bankhierarchy.threading;

import java.util.concurrent.ThreadLocalRandom;

public class Connection {

    private int connectionId;

    public Connection() {
        this.connectionId = ThreadLocalRandom.current().nextInt();
    }

    public int getConnectionId() {
        return connectionId;
    }
}