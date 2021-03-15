package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.List;

@Service
public interface CurrencyService {

    Result save(Currency currency);                   // create

    List<Currency> findAll();                           // read

    Currency findById(Integer id);

    Result edit(Currency currency, Integer id);       // update

    Result delete(Integer id);                          // delete
}
