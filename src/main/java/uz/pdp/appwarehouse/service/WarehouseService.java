package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.List;

@Service
public interface WarehouseService {

    Result save(Warehouse warehouse);                   // create

    List<Warehouse> findAll();                           // read

    Warehouse findById(Integer id);

    Result edit(Warehouse warehouse, Integer id);       // update

    Result delete(Integer id);                          // delete
}
