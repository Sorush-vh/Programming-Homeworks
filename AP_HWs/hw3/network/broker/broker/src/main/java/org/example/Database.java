package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static final Map<String, String> data = new HashMap<>();
    public static String recently_published_topic = null;
    public static List<PushNotification> worked = new ArrayList<>();

    public static synchronized boolean createNewRecord(String key, String value) {
        if (data.get(key) != null)
            return false;
        data.put(key, value);
        return true;
    }


    public static synchronized boolean updateData(String key, String value) {
        if (data.get(key) == null)
            return false;
        data.put(key, value);
        recently_published_topic = key;
        worked.clear();
        return true;
    }

    public static synchronized String getData(String key) {
        return data.get(key);
    }
}
