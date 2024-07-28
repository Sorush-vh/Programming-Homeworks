package org.example;

import com.google.gson.Gson;

import java.io.Serializable;

public class Packet implements Serializable {
    String command;
    String topic;
    String value;

    public Packet(String command, String topic, String value) {
        this.command = command;
        this.topic = topic;
        this.value = value;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
