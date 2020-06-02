package com.xzj.base;

public interface MessageHandler {
    public void onReceive(Connection connection, String message);
}
