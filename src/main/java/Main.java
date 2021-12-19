import exception.CustomerNotFoundException;
import exception.InvalidCommandException;
import lombok.extern.slf4j.Slf4j;
import model.Commands;
import service.impl.ActionHandler;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ActionHandler actionHandler = new ActionHandler();
        for(;;) {
            System.out.println("Please enter command or enter HELP");
            Scanner scannerKey = new Scanner(System.in);
            String actionKey = scannerKey.next();
            if(actionKey.equals(Commands.EXIT.name())) {
                System.out.println("Exit the app");
                log.info("Exit the  app");
                break;
            }
            try{
                actionHandler.readActionKey(actionKey);}
            catch (JAXBException | CustomerNotFoundException | InvalidCommandException e) {
                log.error(Arrays.toString(e.getStackTrace()));
            }

        }
    }
}
