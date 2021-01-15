package tester.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class LogService {
    private LogService() {
    }

    private static Map<String, Logger> loggers;

    static {
        loggers = new HashMap<>();
    }

    public static final void info(Object object, String message) {
        Logger existingLogger = loggers.get(object.getClass().getName());
        if (Objects.isNull(existingLogger)) {
            Logger logger = Logger.getLogger(object.getClass().getName());
            loggers.put(object.getClass().getName(), logger);
            logger.info(message);
        } else {
            existingLogger.info(message);
        }
    }
}
