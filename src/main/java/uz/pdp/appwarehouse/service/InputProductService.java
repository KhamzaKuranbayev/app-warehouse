package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.util.List;

@Service
public interface InputProductService {

    Result save(InputProductDto inputProductDto);                   // create

    List<InputProduct> findAll();                                // read

    InputProduct findById(Integer id);

    Result edit(InputProductDto inputProductDto, Integer id);       // update

    Result delete(Integer id);                                     // delete
}
