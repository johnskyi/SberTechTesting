package service.impl;

import lombok.experimental.UtilityClass;
import model.CustomerStorage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@UtilityClass
final class XMLUtilityImpl {
    private static final String CUSTOMER_STORAGE = "src/main/java/data/customers.xml";
    private static final File FILE_STORAGE = new File(CUSTOMER_STORAGE);

    public static void writeXml(CustomerStorage storage) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(CustomerStorage.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(storage, FILE_STORAGE);
    }
    public static CustomerStorage readXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CustomerStorage.class);
        Unmarshaller um = context.createUnmarshaller();
        CustomerStorage unmarshal = (CustomerStorage) um.unmarshal(FILE_STORAGE);
        return unmarshal;
    }
}
