package com.laba.solvd.bankhierarchy.threading;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private final BlockingDeque<Connection> connections;


    private ConnectionPool() {
        connections = new LinkedBlockingDeque<>();
        initializeConnections(5);
    }

    public synchronized void initializeConnections(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            connections.add(new Connection());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public synchronized Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);

    }
}
