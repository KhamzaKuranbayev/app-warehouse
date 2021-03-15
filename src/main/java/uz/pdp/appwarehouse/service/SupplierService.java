package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Supplier;

import java.util.List;

@Service
public interface SupplierService {

    Result save(Supplier supplier);               // create

    List<Supplier> findAll();                           // read

    Supplier findById(Integer id);

    Result edit(Supplier supplier, Integer id);   // update

    Result delete(Integer id);                          // delete

}
