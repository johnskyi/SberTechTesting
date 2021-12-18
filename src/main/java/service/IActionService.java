package service;

import exception.CustomerNotFoundException;
import model.Customer;
import model.Request;

import javax.xml.bind.JAXBException;

public interface IActionService {
    int saveCustomer(Customer customer) throws JAXBException;
    int deleteCustomer(Request request) throws JAXBException, CustomerNotFoundException;
    int updateCustomer(Request request) throws JAXBException, CustomerNotFoundException;
    Customer findCustomerByPhone(Request request) throws JAXBException, CustomerNotFoundException;

}
