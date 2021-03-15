package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Measurement;

import java.util.List;

@Service
public interface MeasurementService {

    Result save(Measurement measurement);               // create

    List<Measurement> findAll();                        // read

    Measurement findById(Integer id);

    Result edit(Measurement measurement, Integer id);   // update

    Result delete(Integer id);                          // delete

}
