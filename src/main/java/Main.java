import lombok.extern.slf4j.Slf4j;
import service.ActionHandler;
import model.Comands;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Slf4j
public class Main {

    private static final ActionHandler actionHandler = new ActionHandler();

    public static void main(String[] args) {
        for(;;) {
            System.out.println("Please enter command or enter HELP");
            Scanner scannerKey = new Scanner(System.in);
            String actionKey = scannerKey.next();
            if(actionKey.equals(Comands.EXIT.name())) {
                System.out.println("Exit the app");
                log.info("Exit the  app");
                break;
            }
            try{
                actionHandler.readActionKey(actionKey);}
            catch (JAXBException e) {
                e.printStackTrace();
            }

        }
    }
}
