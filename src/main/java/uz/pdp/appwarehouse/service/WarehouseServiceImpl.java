package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Result save(Warehouse warehouse) {
        if(warehouseRepository.existsByName(warehouse.getName()))
            return new Result("Bunday nomli ombor mavjud!", false);

        warehouseRepository.save(warehouse);
        return new Result("Muvaffaqiyatli saqlandi!", true);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElse(null);
    }

    @Override
    public Result edit(Warehouse warehouse, Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            optionalWarehouse.get().setName(warehouse.getName());
            warehouseRepository.save(optionalWarehouse.get());
            return new Result("Ombor ma'lumotlari yangilandi!", true);
        }
        return new Result("Bunday id li ombor mavjud emas!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            warehouseRepository.deleteById(id);
            return new Result("Ombor o'chirildi!", true);
        }
        return new Result("Bunday id li ombor mavjud emas!", false);
    }
}
