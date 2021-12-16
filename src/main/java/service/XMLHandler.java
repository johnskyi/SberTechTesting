package service;

import model.Customer;
import model.CustomerStorage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLHandler {
    private static final String CUSTOMER_STORAGE = "src/main/java/data/customer.xml";

    public static void writeXml(CustomerStorage storage) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(CustomerStorage.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(storage, new File(CUSTOMER_STORAGE));
    }
    public static String getList() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Customer.class);
        Unmarshaller um = context.createUnmarshaller();
        Customer unmarshal = (Customer) um.unmarshal(new File(CUSTOMER_STORAGE));
        return unmarshal.toString();
    }
}
