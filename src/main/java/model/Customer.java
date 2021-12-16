package model;

import com.sun.xml.txw2.annotation.XmlAttribute;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlRootElement(name = "customer")
@XmlType(propOrder = { "uid","name", "lastname", "patronymic", "company","email","phone" })
public class Customer {

    int uid;
    String name;
    String lastname;
    String patronymic;
    String company;
    String email;
    String phone;

    @XmlAttribute
    public int getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "Customer" +
                "uid =" + uid +
                ", name ='" + name + '\'' +
                ", lastname ='" + lastname + '\'' +
                ", patronymic ='" + patronymic + '\'' +
                ", company ='" + company + '\'' +
                ", email ='" + email + '\'' +
                ", phones =" + phone;
    }
}
