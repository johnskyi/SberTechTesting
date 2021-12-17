import exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Commands;
import service.impl.ActionHandlerImpl;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ActionHandlerImpl actionHandler = new ActionHandlerImpl();
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
            catch (JAXBException | CustomerNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
