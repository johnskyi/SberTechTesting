package model;

import com.sun.xml.txw2.annotation.XmlAttribute;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlRootElement(name = "customer")
@XmlType(propOrder = { "uid","name", "lastname", "patronymic", "company","email","phone" })
public class Customer {

    String uid;
    String name;
    String lastname;
    String company;
    String email;
    String phone;

    @XmlAttribute
    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "Customer" +
                "uid =" + uid +
                ", name ='" + name + '\'' +
                ", lastname ='" + lastname + '\'' +
                ", company ='" + company + '\'' +
                ", email ='" + email + '\'' +
                ", phones =" + phone;
    }
}
