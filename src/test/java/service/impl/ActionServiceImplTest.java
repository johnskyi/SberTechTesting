package service.impl;

import exception.CustomerNotFoundException;
import model.Customer;
import model.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActionServiceImplTest {
    private static Customer testCustomerOne;

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
    ActionService actionServiceMock;

    @Test
    @DisplayName("Save customer, return uid")
    void saveCustomer() throws JAXBException {
        Mockito.when(actionServiceMock.saveCustomer(testCustomerOne)).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionServiceMock.saveCustomer(testCustomerOne);
        Mockito.verify(actionServiceMock,Mockito.times(1)).saveCustomer(testCustomerOne);
        assertEquals(123,actionServiceMock.saveCustomer(testCustomerOne));
    }

    @Test
    @DisplayName("Deleted customer, return uid")
    void deleteCustomer() throws JAXBException, CustomerNotFoundException {
        Request request = Request.builder().lastname("Ivanov").build();
        Mockito.when(actionServiceMock.deleteCustomer(request)).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionServiceMock.deleteCustomer(request);
        Mockito.verify(actionServiceMock,Mockito.times(1)).deleteCustomer(request);
        assertEquals(123,actionServiceMock.deleteCustomer(request));
    }

    @Test
    @DisplayName("Updated customer, return uid")
    void updateCustomer() throws JAXBException, CustomerNotFoundException {
        Request request = Request.builder().lastname("Ivanov").company("Magnet").build();
        Mockito.when(actionServiceMock.updateCustomer(request)).thenReturn(Integer.parseInt(testCustomerOne.getUid()));
        actionServiceMock.updateCustomer(request);
        Mockito.verify(actionServiceMock,Mockito.times(1)).updateCustomer(request);
        assertEquals(123,actionServiceMock.updateCustomer(request));
    }

    @Test
    @DisplayName("Find customer, return customer")
    void findCustomerByPhone() throws JAXBException, CustomerNotFoundException {
        Request request = Request.builder().phone("12345").build();
        Mockito.when(actionServiceMock.findCustomerByPhone(request)).thenReturn(testCustomerOne);
        actionServiceMock.findCustomerByPhone(request);
        Mockito.verify(actionServiceMock,Mockito.times(1)).findCustomerByPhone(request);
        assertEquals(testCustomerOne,actionServiceMock.findCustomerByPhone(request));
    }
    @Test
    @DisplayName("Find unknown customer throws customerNotFound")
    void findCustomerByPhone_throwsCustomerNotFound() throws JAXBException, CustomerNotFoundException {
        Request request = Request.builder().phone("123").build();
        Mockito.when(actionServiceMock.findCustomerByPhone(Mockito.any())).thenThrow(CustomerNotFoundException.class);
        assertThrows(CustomerNotFoundException.class, () ->actionServiceMock.findCustomerByPhone(request));
    }
    @Test
    @DisplayName("Find unknown customer throws customerNotFound")
    void updateCustomer_throwsCustomerNotFound() throws JAXBException, CustomerNotFoundException {
        Request request = Request.builder().lastname("Petrov").build();
        Mockito.when(actionServiceMock.updateCustomer(Mockito.any())).thenThrow(CustomerNotFoundException.class);
        assertThrows(CustomerNotFoundException.class, () ->actionServiceMock.updateCustomer(request));
    }

}