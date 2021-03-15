package uz.pdp.appwarehouse.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private Set<Integer> warehouseIdList;
}
