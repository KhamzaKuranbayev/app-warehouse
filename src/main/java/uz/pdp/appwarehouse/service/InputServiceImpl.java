package uz.pdp.appwarehouse.service;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputServiceImpl implements InputService{

    final InputRepository inputRepository;
    final WarehouseRepository warehouseRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;

    public InputServiceImpl(InputRepository inputRepository, WarehouseRepository warehouseRepository,
                            SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Result save(InputDto inputDto) {

        if(inputRepository.existsByFactureNumber(inputDto.getFactureNumber()))
            return new Result("Bunday facturali kirim bo'lgan!", false);

        Optional<Warehouse> optionalWarehouse =  warehouseRepository.findById(inputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent())
            return new Result("Bunday id li ombor mavjud emas!", false);

        Optional<Supplier> optionalSupplier =  supplierRepository.findById(inputDto.getSupplierId());
        if(!optionalSupplier.isPresent())
            return new Result("Bunday id li yetkazib beruvchi mavjud emas!", false);

        Optional<Currency> optionalCurrency =  currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Bunday id li valyuta mavjud emas!", false);

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(ProductServiceImpl.generateUUIDCode());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        inputRepository.save(input);

        return new Result("Kirim bazaga saqlandi!", true);
    }

    @Override
    public List<Input> findAll() {
        return inputRepository.findAll();
    }

    @Override
    public Input findById(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    @Override
    public Result edit(InputDto inputDto, Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isPresent()){

            Optional<Warehouse> optionalWarehouse =  warehouseRepository.findById(inputDto.getWarehouseId());
            if(!optionalWarehouse.isPresent())
                return new Result("Bunday id li ombor mavjud emas!", false);

            Optional<Supplier> optionalSupplier =  supplierRepository.findById(inputDto.getSupplierId());
            if(!optionalSupplier.isPresent())
                return new Result("Bunday id li yetkazib beruvchi mavjud emas!", false);

            Optional<Currency> optionalCurrency =  currencyRepository.findById(inputDto.getCurrencyId());
            if(!optionalCurrency.isPresent())
                return new Result("Bunday id li valyuta mavjud emas!", false);

            optionalInput.get().setDate(inputDto.getDate());
            optionalInput.get().setFactureNumber(inputDto.getFactureNumber());
            optionalInput.get().setWarehouse(optionalWarehouse.get());
            optionalInput.get().setSupplier(optionalSupplier.get());
            optionalInput.get().setCurrency(optionalCurrency.get());

            inputRepository.save(optionalInput.get());

            return new Result("Kirim ma'lumotlari o'zgartirildi!", true);
        }
        return new Result("Bunday id li kirim topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new Result("Kirim o'chirildi!", true);
        }
        return new Result("Bunday id li kirim topilmadi!", false);
    }
}
