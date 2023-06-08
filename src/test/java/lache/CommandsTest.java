package lache;

import lache.common.Commands;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandsTest {
    @ParameterizedTest
    @EnumSource(Commands.class)
    void testGetCommand(Commands command) {
        assertNotNull(command);
    }
}
