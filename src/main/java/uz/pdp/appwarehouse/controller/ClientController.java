package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Result save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping
    public List<Client> getAll(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Integer id) {
        return clientService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody Client client, @PathVariable Integer id) {
        return clientService.edit(client, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return clientService.delete(id);
    }
}
