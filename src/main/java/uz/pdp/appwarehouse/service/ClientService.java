package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.List;

@Service
public interface ClientService {

    Result save(Client client);                   // create

    List<Client> findAll();                           // read

    Client findById(Integer id);

    Result edit(Client client, Integer id);         // update

    Result delete(Integer id);                          // delete
}
