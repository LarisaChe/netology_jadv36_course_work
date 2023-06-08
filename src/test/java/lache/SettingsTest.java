package lache;

import lache.common.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsTest {

    @ParameterizedTest
    @CsvSource({
            "settingsServerTest.txt, Server",
            "settingsClientTest.txt, Client"
    })
    public void testCheckSettingsFile(String fn, String type)  {
        Assertions.assertDoesNotThrow(() -> Settings.checkSettingsFile(fn,  type));
    }

    @Test
    public void testGetSettingsServer() throws IOException {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("port", "8095");
        Map<String, String> gotMap = Settings.getSettings("settingsServerTest.txt");
        Assertions.assertTrue(gotMap.equals(testMap));
    }

    @Test
    public void testGetSettingsClient() throws IOException {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("host", "netology.homework");
        testMap.put("port", "8095");
        Map<String, String> gotMap = Settings.getSettings("settingsClientTest.txt");
        Assertions.assertTrue(gotMap.equals(testMap));
    }
}
