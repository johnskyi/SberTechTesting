package service.impl;

import exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Customer;
import model.CustomerStorage;
import model.Request;
import service.IActionService;

import javax.xml.bind.JAXBException;

@Slf4j
public class ActionService implements IActionService {

    @Override
    public int saveCustomer(Customer customer) throws JAXBException {
        CustomerStorage storage = XMLUtility.readXml();
        storage.getCustomers().add(customer);
        XMLUtility.writeXml(storage);
        System.out.println("Saved");
        log.info("Save customer " + customer.getLastname());
        return Integer.parseInt(customer.getUid());
    }

    @Override
    public int deleteCustomer(Request request) throws JAXBException, CustomerNotFoundException {
        CustomerStorage storage = XMLUtility.readXml();
        Customer customer = findCustomerByLastName(storage,request.getLastname());
        storage.getCustomers().remove(customer);
        XMLUtility.writeXml(storage);
        System.out.println("Deleted");
        log.info("Delete customer " + customer.getLastname());
        return Integer.parseInt(customer.getUid());
    }

    @Override
    public int updateCustomer(Request request) throws JAXBException, CustomerNotFoundException {
        CustomerStorage storage = XMLUtility.readXml();
        Customer customer = findCustomerByLastName(storage,request.getLastname());
        switch (request.getTypeUpdate()) {
            case "name" -> customer.setName(request.getName());
            case "lastname" -> customer.setLastname(request.getNewLastName());
            case "company" -> customer.setCompany(request.getCompany());
            case "email" -> customer.setEmail(request.getEmail());
            case "phone" -> customer.setPhone(request.getPhone());
        }
        XMLUtility.writeXml(storage);
        System.out.println("Updated");
        log.info("Updated customer " + customer.getLastname());
        return Integer.parseInt(customer.getUid());
    }

    @Override
    public Customer findCustomerByPhone(Request request) throws JAXBException, CustomerNotFoundException {
        CustomerStorage storage = XMLUtility.readXml();
        return storage.getCustomers().stream()
                .filter(customer -> customer.getPhone().equals(request.getPhone()))
                .findFirst().orElseThrow(() -> new CustomerNotFoundException(request.getPhone()));
    }
    private Customer findCustomerByLastName(CustomerStorage storage,String lastname) throws CustomerNotFoundException {
        return storage.getCustomers().stream()
                .filter(c -> c.getLastname().equals(lastname))
                .findFirst().orElseThrow(() -> new CustomerNotFoundException(lastname));
    }
}
