package service.impl;

import exception.CustomerNotFoundException;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionHandlerImplTest {
   private  Customer testCustomerOne;

    @BeforeEach
     void setUp() {
        testCustomerOne = new Customer();
        testCustomerOne.setName("Ivan");
        testCustomerOne.setLastname("Ivanov");
        testCustomerOne.setCompany("Lenta");
        testCustomerOne.setEmail("test@test.ru");
        testCustomerOne.setPhone("12345");
        testCustomerOne.setUid("123");
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    ActionHandler actionHandlerMock;


    @Test
    @DisplayName("Save customer, return uid")
    void save() throws JAXBException {
        Mockito.when(actionHandlerMock.save()).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionHandlerMock.save();
        Mockito.verify(actionHandlerMock,Mockito.times(1)).save();
        assertEquals(123,actionHandlerMock.save());
    }

    @Test
    @DisplayName("Delete customer, return uid")
    void delete() throws JAXBException, CustomerNotFoundException {
        Mockito.when(actionHandlerMock.delete()).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionHandlerMock.delete();
        Mockito.verify(actionHandlerMock,Mockito.times(1)).delete();
        assertEquals(123,actionHandlerMock.delete());
    }

    @Test
    @DisplayName("Update customer, return uid")
    void update() throws JAXBException, CustomerNotFoundException {
        Mockito.when(actionHandlerMock.update()).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionHandlerMock.update();
        Mockito.verify(actionHandlerMock,Mockito.times(1)).update();
        assertEquals(123,actionHandlerMock.update());
    }

    @Test
    @DisplayName("Find customer, return customer")
    void findByPhone() throws JAXBException, CustomerNotFoundException {
        Mockito.when(actionHandlerMock.findByPhone()).thenReturn(testCustomerOne);
        actionHandlerMock.findByPhone();
        Mockito.verify(actionHandlerMock,Mockito.times(1)).findByPhone();
        assertEquals(testCustomerOne,actionHandlerMock.findByPhone());
    }
}