package org.organizerweb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest
{
    private Logger logger;

    @BeforeEach
    public void setUp() {
        logger = new Logger();
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test2"})
    public void testLog(String logMessage) {
        logger.log(logMessage);
        assertTrue(logger.getLogs().getLast().contains(logMessage), "Log should contain the message");
    }
}
