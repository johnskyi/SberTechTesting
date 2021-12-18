package service;

import exception.CustomerNotFoundException;
import model.Customer;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IActionHandler {
    int save() throws JAXBException;
    int delete() throws JAXBException, CustomerNotFoundException;
    int update() throws JAXBException, CustomerNotFoundException;
    Customer findByPhone() throws JAXBException, CustomerNotFoundException;
    List<Customer> getCustomers() throws ParserConfigurationException, IOException, SAXException, JAXBException;
}
