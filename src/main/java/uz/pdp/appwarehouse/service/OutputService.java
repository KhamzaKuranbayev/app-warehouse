package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.OutputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Output;

import java.util.List;

@Service
public interface OutputService {

    Result save(OutputDto outputDto);                   // create

    List<Output> findAll();                           // read

    Output findById(Integer id);

    Result edit(OutputDto outputDto, Integer id);       // update

    Result delete(Integer id);                          // delete
}
