package service.impl;

import model.Customer;
import model.CustomerStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

class ActionServiceImplTest {
   private static Customer testCustomerOne;
   private static Customer testCustomerTwo;
   private static CustomerStorage testStorage;
    @BeforeAll
    static void setUp() {
        testCustomerOne = new Customer();
        testCustomerOne.setName("Ivan");
        testCustomerOne.setLastname("Ivanov");
        testCustomerOne.setCompany("Lenta");
        testCustomerOne.setEmail("test@test.ru");
        testCustomerOne.setPhone("12345");
        testCustomerOne.setUid(String.valueOf(testCustomerOne.hashCode()));
        testCustomerTwo = new Customer();
        testCustomerTwo.setName("Petr");
        testCustomerTwo.setLastname("Petrov");
        testCustomerTwo.setCompany("Magnit");
        testCustomerTwo.setEmail("test2@test.ru");
        testCustomerTwo.setPhone("54321");
        testCustomerTwo.setUid(String.valueOf(testCustomerTwo.hashCode()));
        testStorage = new CustomerStorage(new ArrayList<>());
    }

    @Test
    void saveCustomer() throws JAXBException {
        ActionServiceImpl actionServiceMock = Mockito.mock(ActionServiceImpl.class);
        actionServiceMock.saveCustomer(testCustomerOne);
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void findCustomerByPhone() {
    }
}