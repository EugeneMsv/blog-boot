package ru.text.nastya.profiling;

import org.slf4j.Logger;

public enum LogLevel {

    TRACE {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.trace(pattern, objects);
        }

        @Override
        public boolean isEnabled(Logger logger) {
            return logger.isTraceEnabled();
        }
    }, INFO {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.info(pattern, objects);
        }

        @Override
        public boolean isEnabled(Logger logger) {
            return logger.isInfoEnabled();
        }
    }, DEBUG {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.debug(pattern, objects);
        }

        @Override
        public boolean isEnabled(Logger logger) {
            return logger.isDebugEnabled();
        }
    }, ERROR {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.error(pattern, objects);
        }

        @Override
        public boolean isEnabled(Logger logger) {
            return logger.isErrorEnabled();
        }
    };

    private static final String INPUT_PATTERN = "Called='{}'; method='{}'; args='{}';";
    private static final String OUTPUT_PATTERN = "Called='{}'; method='{}'; returnValue='{}';";

    public void callLogger(Logger logger, Class<?> targetClass, String signatureName) {
        callLogger(logger, targetClass, signatureName, null);
    }

    public void callLogger(Logger logger, Class<?> targetClass, String signatureName, Object[] args) {
        log(logger, INPUT_PATTERN, targetClass, signatureName, args);
    }

    public void callLogger(Logger logger, Class<?> targetClass, String signatureName, Object proceedResult) {
        log(logger, OUTPUT_PATTERN, targetClass, signatureName, proceedResult);
    }

    abstract void log(Logger logger, String pattern, Object... objects);

    public abstract boolean isEnabled(Logger logger);
}
