package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputProductDto;
import uz.pdp.appwarehouse.dtos.OutputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;

import java.util.List;

@Service
public interface OutputProductService {

    Result save(OutputProductDto outputProductDto);                   // create

    List<OutputProduct> findAll();                                   // read

    OutputProduct findById(Integer id);

    Result edit(OutputProductDto outputProductDto, Integer id);      // update

    Result delete(Integer id);                                       // delete
}
