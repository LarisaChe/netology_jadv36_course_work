package lache.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    public static Map<String, String> getSettings(String fileName) throws IOException {
        Map<String, String> result = new HashMap<>();
        List<String> stringsFromFile = WorkWithFiles.getListOfStringsFromFile(fileName);
        for (String str : stringsFromFile) {
            String[] words = str.split("=");
            if (words.length == 2) {
                result.put(words[0].trim(), words[1].trim());
            }
        }
        return result;
    }

    public static void checkSettingsFile (String fn, String type) throws IOException {
        if (!(new File(fn).exists())) {
            if (type.equals("Server")) {
                WorkWithFiles.saveToFile(fn, "port = 8095", false);
            }
            if (type.equals("Client")) {
                WorkWithFiles.saveToFile(fn, "host = netology.homework", false);
                WorkWithFiles.saveToFile(fn, "port = 8095", true);
            }
        }
    }
}
