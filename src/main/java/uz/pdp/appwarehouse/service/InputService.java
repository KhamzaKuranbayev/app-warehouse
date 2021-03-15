package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;

import java.util.List;

@Service
public interface InputService {

    Result save(InputDto inputDto);                   // create

    List<Input> findAll();                           // read

    Input findById(Integer id);

    Result edit(InputDto inputDto, Integer id);       // update

    Result delete(Integer id);                          // delete
}
