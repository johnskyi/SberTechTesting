package service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Comands;
import model.Customer;
import model.CustomerStorage;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

@NoArgsConstructor
@Slf4j
public class ActionHandler {

    private final CustomerStorage customerStorage = new CustomerStorage(new ArrayList<>());


    public void readActionKey(String actionKey) throws JAXBException {
        process(actionKey);
    }
    private void process(String action) throws JAXBException {
        if(action.equals(Comands.INSERT.name())) {
            saveCustomer();
        }
        if (action.equals(Comands.DELETE.name())) {
            deleteCustomer();
        }
        if (action.equals(Comands.HELP.name())) {
            getHelpInfo();
        }
        if (action.equals(Comands.UPDATE.name())) {
            updateCustomer();
        }
        if (action.equals(Comands.LIST.name())) {
            System.out.println(getCustomersList());
        }
        if (action.equals(Comands.FIND.name())) {
            System.out.println(findCustomerByPhone());
        }
        
    }
    private void getHelpInfo() {
        String str = "INSERT - action for save customer \n" +
                "DELETE - action for delete customer \n" +
                "UPDATE - update customer`s info \n" +
                "LIST - for getting customers`s list \n" +
                "FIND - for search customer by phone \n" +
                "EXIT - for exit the app";
        System.out.println(str);
    }
    private void saveCustomer() throws JAXBException {
        Customer customer = new Customer();
        System.out.println("Enter name");
        customer.setName(new Scanner(System.in).next());
        System.out.println("Enter lastname");
        customer.setLastname(new Scanner(System.in).next());
        System.out.println("Enter patronymic");
        customer.setPatronymic(new Scanner(System.in).next());
        System.out.println("Enter company");
        customer.setCompany(new Scanner(System.in).next());
        System.out.println("Enter email");
        customer.setEmail(new Scanner(System.in).next());
        System.out.println("Enter phone in format 89173335522");
        customer.setPhone(new Scanner(System.in).next());
        customer.setUid(customer.hashCode());
        customerStorage.getCustomers().add(customer);
        XMLHandler.writeXml(customerStorage);
        System.out.println("Saved");
        log.info("Save customer " + customer.getName());
    }
    private void deleteCustomer() throws JAXBException {
        System.out.println("Enter lastname");
        String lastname = new Scanner(System.in).next();
        customerStorage.getCustomers().remove(findByLastName(lastname));
        XMLHandler.writeXml(customerStorage);
        log.info("Delete customer " + lastname);
    }
    private void  updateCustomer() throws JAXBException {
        System.out.println("Enter lastname");
        String lastname = new Scanner(System.in).next();
        Customer customer = findByLastName(lastname);
        int index = customerStorage.getCustomers().indexOf(customer);
        System.out.println("Enter type info for update");
        String typeInfo = new Scanner(System.in).next();
        switchAndUpdate(typeInfo, index);
        System.out.println("Updated");
        log.info("Update customer " + lastname);
    }

    private String getCustomersList() throws JAXBException {
       return XMLHandler.getList();
    }
    private String findCustomerByPhone() {
        System.out.println("Enter customer`s phone");
        String phone = new Scanner(System.in).next();
        Customer customer = customerStorage.getCustomers()
                .stream().filter(c -> c.getPhone().equals(phone)).findFirst().get();
        log.info("Find customer by phone " + phone);
        return customer.toString();
    }
    private Customer findByLastName(String lastname) {
       return customerStorage.getCustomers()
                .stream()
                .filter(obj -> obj.getLastname().equals(lastname)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    private void switchAndUpdate(String typeInfo, int index) throws JAXBException {
        switch (typeInfo) {
            case "name" -> {
                System.out.println("Enter name");
                customerStorage.getCustomers().get(index).setName(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
            case "lastname" -> {
                System.out.println("Enter lastname");
                customerStorage.getCustomers().get(index).setLastname(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
            case "patronymic" -> {
                System.out.println("Enter patronymic");
                customerStorage.getCustomers().get(index).setPatronymic(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
            case "company" -> {
                System.out.println("Enter company");
                customerStorage.getCustomers().get(index).setCompany(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
            case "email" -> {
                System.out.println("Enter email");
                customerStorage.getCustomers().get(index).setEmail(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
            case "phone" -> {
                System.out.println("Enter phone");
                customerStorage.getCustomers().get(index).setPhone(new Scanner(System.in).next());
                XMLHandler.writeXml(customerStorage);
            }
        }
    }
}
