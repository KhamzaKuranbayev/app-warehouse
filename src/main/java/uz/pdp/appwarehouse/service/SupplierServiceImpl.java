package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{

    final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Result save(Supplier supplier) {
        if(supplierRepository.existsByNameAndPhoneNumber(supplier.getName(), supplier.getPhoneNumber()))
            return new Result("Bunday yetkazib beruvchi bazada mavjud!", false);

        supplierRepository.save(supplier);
        return new Result("Yetkazib beruvchi saqlandi!", true);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    @Override
    public Result edit(Supplier supplier, Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()){
            optionalSupplier.get().setName(supplier.getName());
            optionalSupplier.get().setPhoneNumber(supplier.getPhoneNumber());

            supplierRepository.save(optionalSupplier.get());

            return new Result("Yetkazib beruvchi ma'lumotlari tahrirlandi!", true);
        }

        return new Result("Bunday yetkazib beruvchi topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
            return new Result("Yetkazib beruvchi o'chirildi!", true);
        }
        return new Result("Bunday yetkazib beruvchi topilmadi!", false);
    }
}
