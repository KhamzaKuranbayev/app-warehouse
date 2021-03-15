package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.OutputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputServiceImpl implements OutputService {

    final OutputRepository outputRepository;
    final WarehouseRepository warehouseRepository;
    final ClientRepository clientRepository;
    final CurrencyRepository currencyRepository;

    public OutputServiceImpl(OutputRepository outputRepository, WarehouseRepository warehouseRepository,
                             ClientRepository clientRepository, CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Result save(OutputDto outputDto) {
        if (outputRepository.existsByFactureNumber(outputDto.getFactureNumber()))
            return new Result("Bunday facturali chiqim qilingan!", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday id li ombor mavjud emas!", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday id li mijoz mavjud emas!", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday id li valyuta mavjud emas!", false);

        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(ProductServiceImpl.generateUUIDCode());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());

        outputRepository.save(output);

        return new Result("Chiqim bazaga saqlandi!", true);
    }

    @Override
    public List<Output> findAll() {
        return outputRepository.findAll();
    }

    @Override
    public Output findById(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    @Override
    public Result edit(OutputDto outputDto, Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
            if (!optionalWarehouse.isPresent())
                return new Result("Bunday id li ombor mavjud emas!", false);

            Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
            if (!optionalClient.isPresent())
                return new Result("Bunday id li yetkazib beruvchi mavjud emas!", false);

            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
            if (!optionalCurrency.isPresent())
                return new Result("Bunday id li valyuta mavjud emas!", false);

            optionalOutput.get().setDate(outputDto.getDate());
            optionalOutput.get().setFactureNumber(outputDto.getFactureNumber());
            optionalOutput.get().setWarehouse(optionalWarehouse.get());
            optionalOutput.get().setClient(optionalClient.get());
            optionalOutput.get().setCurrency(optionalCurrency.get());

            outputRepository.save(optionalOutput.get());

            return new Result("Chiqim ma'lumotlari o'zgartirildi!", true);
        }
        return new Result("Bunday id li chiqim topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            outputRepository.deleteById(id);
            return new Result("Chiqim o'chirildi!", true);
        }
        return new Result("Bunday id li chiqim topilmadi!", false);
    }
}
