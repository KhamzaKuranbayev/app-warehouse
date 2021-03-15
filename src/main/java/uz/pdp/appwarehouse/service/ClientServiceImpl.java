package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    
    final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Result save(Client client) {
        if(clientRepository.existsByNameAndPhoneNumber(client.getName(), client.getPhoneNumber()))
            return new Result("Bunday mijoz bazada mavjud!", false);

        clientRepository.save(client);
        return new Result("Mijoz saqlandi!", true);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    @Override
    public Result edit(Client client, Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            optionalClient.get().setName(client.getName());
            optionalClient.get().setPhoneNumber(client.getPhoneNumber());

            clientRepository.save(optionalClient.get());

            return new Result("Mijoz ma'lumotlari tahrirlandi!", true);
        }

        return new Result("Bunday mijoz topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return new Result("Mijoz o'chirildi!", true);
        }
        return new Result("Bunday mijoz topilmadi!", false);
    }
}
