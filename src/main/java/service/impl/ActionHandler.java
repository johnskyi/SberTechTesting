package service.impl;

import exception.CustomerNotFoundException;
import exception.InvalidCommandException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Commands;
import model.Customer;
import model.Request;
import service.IActionHandler;
import service.IActionService;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Slf4j
public class ActionHandler implements IActionHandler {


    private final IActionService actionService = new ActionService();



    public void readActionKey(String actionKey) throws JAXBException, CustomerNotFoundException, InvalidCommandException {
        if(validateInputAction(actionKey)) {
            switchNextMethod(actionKey);
        } else {
            System.out.println("Invalid command");
            throw new InvalidCommandException(actionKey);

        }
    }
    void switchNextMethod(String action) throws JAXBException, CustomerNotFoundException {
        if(action.equals(Commands.INSERT.name())) {
            save();
        }
        if (action.equals(Commands.DELETE.name())) {
            delete();
        }
        if (action.equals(Commands.HELP.name())) {
            System.out.println(getHelpInfo());
        }
        if (action.equals(Commands.UPDATE.name())) {
            update();
        }
        if (action.equals(Commands.LIST.name())) {
            System.out.println(getCustomers());
        }
        if (action.equals(Commands.FIND.name())) {
            findByPhone();
        }

    }
     String getHelpInfo() {
        return  "INSERT - action for save customer \n" +
                "DELETE - action for delete customer \n" +
                "UPDATE - update customer`s info \n" +
                "LIST - for getting customers`s list \n" +
                "FIND - for search customer by phone \n" +
                "EXIT - for exit the app";
    }
    private Boolean validateInputAction(String action) {
        return action.matches("INSERT|FIND|DELETE|UPDATE|EXIT|HELP|LIST");
    }

    @Override
    public int save() throws JAXBException {
        Customer customer = new Customer();
        System.out.println("Enter name");
        customer.setName(new Scanner(System.in).next());
        System.out.println("Enter lastname");
        customer.setLastname(new Scanner(System.in).next());
        System.out.println("Enter company");
        customer.setCompany(new Scanner(System.in).next());
        System.out.println("Enter email");
        customer.setEmail(new Scanner(System.in).next());
        System.out.println("Enter phone");
        customer.setPhone(new Scanner(System.in).next());
        customer.setUid(String.valueOf(customer.hashCode()));
        return actionService.saveCustomer(customer);
    }

    @Override
    public int delete() throws JAXBException, CustomerNotFoundException {
        System.out.println("Enter customers lastname");
        Request request = Request.builder()
                .lastname(new Scanner(System.in).next())
                .build();
       return actionService.deleteCustomer(request);
    }

    @Override
    public int update() throws JAXBException, CustomerNotFoundException {
        System.out.println("Enter customers lastname");
        String lastname = new Scanner(System.in).next();
        System.out.println("Enter type info for updated (ex: name/lastname/company/email/phone)");
        String typeUpdatedInfo = new Scanner(System.in).next();
        Request request;
        switch (typeUpdatedInfo) {
            case "name" -> {
                System.out.println("Enter name");
                request = Request.builder().name(new Scanner(System.in).next())
                        .lastname(lastname).typeUpdate("name").build();
               return actionService.updateCustomer(request);
            }
            case "lastname" -> {
                System.out.println("Enter lastname");
                request = Request.builder()
                        .newLastName(new Scanner(System.in).next())
                        .lastname(lastname)
                        .typeUpdate("lastname").build();
                return actionService.updateCustomer(request);
            }
            case "company" -> {
                System.out.println("Enter company");
                request = Request.builder().company(new Scanner(System.in).next())
                        .lastname(lastname).typeUpdate("company").build();
                return actionService.updateCustomer(request);
            }
            case "email" -> {
                System.out.println("Enter email");
                 request = Request.builder().email(new Scanner(System.in).next())
                         .lastname(lastname).typeUpdate("email").build();
                return  actionService.updateCustomer(request);
            }
            case "phone" -> {
                System.out.println("Enter phone");
                request = Request.builder().phone(new Scanner(System.in).next())
                        .lastname(lastname).typeUpdate("phone").build();
                return actionService.updateCustomer(request);
            }
        }
        return 0;
    }

    @Override
    public Customer findByPhone() throws JAXBException, CustomerNotFoundException {
        System.out.println("Enter customers phone");
        Request request = Request.builder()
                .phone(new Scanner(System.in).next())
                .build();
       return actionService.findCustomerByPhone(request);
    }

    @Override
    public List<Customer> getCustomers() throws JAXBException {
        return XMLUtility.readXml().getCustomers();
    }
}
