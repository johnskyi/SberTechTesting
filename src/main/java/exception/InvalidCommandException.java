package exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String command) {
        log.error("Invalid command " + command);

    }
}
