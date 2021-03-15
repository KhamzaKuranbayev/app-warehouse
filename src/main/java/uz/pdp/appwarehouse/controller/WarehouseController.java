package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Result save(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse getOne(@PathVariable Integer id) {
        return warehouseService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody Warehouse warehouse, @PathVariable Integer id) {
        return warehouseService.edit(warehouse, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return warehouseService.delete(id);
    }

}
