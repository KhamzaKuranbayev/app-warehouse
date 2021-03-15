package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.dtos.UserDto;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.service.SupplierService;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody User user, @PathVariable Integer id) {
        return userService.edit(user, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return userService.delete(id);
    }

}
