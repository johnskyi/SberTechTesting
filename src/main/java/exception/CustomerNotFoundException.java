package exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String request) {
        super("Customer with  lastname " + request + " not found");
        System.out.println("Customer with  lastname " + request + " not found");
        log.error("Customer with lastname " + request + " not found");

    }
    public CustomerNotFoundException(int request) {
        super("Customer with  phone " + request + " not found");
        System.out.println("Customer with  lastname " + request + " not found");
        log.error("Customer with phone " + request + " not found");

    }
}
