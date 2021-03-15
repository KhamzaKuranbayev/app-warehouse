package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public Result save(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id) {
        return supplierService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody Supplier supplier, @PathVariable Integer id) {
        return supplierService.edit(supplier, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }

}
