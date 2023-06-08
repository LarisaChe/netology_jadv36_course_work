package lache;

import lache.common.WorkWithFiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

public class WorkWithFilesTest {

    @BeforeAll
    public static void testSaveToFileOneString()  {
        Assertions.assertDoesNotThrow(() -> WorkWithFiles.saveToFile("testSettings.txt",  "port = 4096",  false));
    }

    @ParameterizedTest
    @CsvSource({
            "test.log, первая тестовая информация, true",
            "test.log, вторая, true",
            "test.log, еще одна, true",
            "test.log, финальная, true"
    })
    public void testSaveToFile(String fn, String str, boolean append)  {
        Assertions.assertDoesNotThrow(() -> WorkWithFiles.saveToFile(fn,  str,  append) );
    }

    @Test
    public void testGetListOfStringsFromFile()  {
        List<String> testStr = Arrays.asList("port = 4096");
        List<String> gotStr = WorkWithFiles.getListOfStringsFromFile("testSettings.txt");
        Assertions.assertTrue(gotStr.equals(testStr));

    }
}
