package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(namespace = "customers.storage")
@Getter
@NoArgsConstructor
public class CustomerStorage {
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private ArrayList<Customer> customers;

    public CustomerStorage(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
