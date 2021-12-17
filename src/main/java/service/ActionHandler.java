package service;

import exception.CustomerNotFoundException;
import model.Customer;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ActionHandler {
    void save() throws JAXBException;
    void delete() throws JAXBException, CustomerNotFoundException;
    void update() throws JAXBException, CustomerNotFoundException;
    void findByPhone() throws JAXBException, CustomerNotFoundException;
    List<Customer> getCustomers() throws ParserConfigurationException, IOException, SAXException, JAXBException;
}
