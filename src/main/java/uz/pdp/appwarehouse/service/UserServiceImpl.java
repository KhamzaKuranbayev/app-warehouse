package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.dtos.UserDto;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final WarehouseRepository warehouseRepository;

    public UserServiceImpl(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Result save(UserDto userDto) {
        if (userRepository.existsByFirstnameAndLastnameAndPhoneNumber(userDto.getFirstname(), userDto.getLastname(), userDto.getPhoneNumber()))
            return new Result("Bunday foydalanuvchi bazada mavjud!", false);

        User user = new User();
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setCode(ProductServiceImpl.generateUUIDCode());

        Set<Integer> warehouseIdList = userDto.getWarehouseIdList();
        Set<Warehouse> warehouses = new HashSet<>();

        for (Integer warehouseId : warehouseIdList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            optionalWarehouse.ifPresent(warehouses::add);
        }
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("Foydalanuvchi saqlandi!", true);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public Result edit(User user, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setFirstname(user.getFirstname());
            optionalUser.get().setLastname(user.getLastname());
            optionalUser.get().setPhoneNumber(user.getPhoneNumber());
            optionalUser.get().setPassword(user.getPassword());
            optionalUser.get().setCode(ProductServiceImpl.generateUUIDCode()); // UUID generatsiya qiladi

            userRepository.save(optionalUser.get());
            return new Result("Foydalanuvchi ma'lumotlari tahrirlandi!", true);
        }

        return new Result("Bunday foydalanuvchi topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return new Result("Foydalanuvchi o'chirildi!", true);
        }
        return new Result("Bunday foydalanuvchi topilmadi!", false);
    }
}
