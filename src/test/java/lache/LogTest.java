package lache;

import lache.common.Log;
import lache.common.WorkWithFiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

public class LogTest {

    @Test
    public void testGetSettingsServer() throws IOException {
        Log log = Log.getInstance();
        log.log("Test1", "Test2", "Проверка!");
        String testStr = "Test2: Проверка!";
        List<String> gotList = WorkWithFiles.getListOfStringsFromFile("Test1_chat.log");
        String[] arrStr = gotList.get(0).split("]");
        String gotStr = arrStr[1].trim();
        Assertions.assertTrue(gotStr.equals(testStr));
    }

}
