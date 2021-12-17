package model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Request {
    String name;
    String lastname;
    String newLastName;
    String company;
    String email;
    String phone;
    String typeUpdate;
}
