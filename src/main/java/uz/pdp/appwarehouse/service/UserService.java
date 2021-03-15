package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.dtos.UserDto;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.User;

import java.util.List;

@Service
public interface UserService {

    Result save(UserDto userDto);                   // create

    List<User> findAll();                           // read

    User findById(Integer id);

    Result edit(User user, Integer id);             // update

    Result delete(Integer id);                      // delete

}
