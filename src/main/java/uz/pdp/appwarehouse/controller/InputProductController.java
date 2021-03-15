package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.InputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    final InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping
    public Result save(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.save(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getAll() {
        return inputProductService.findAll();
    }

    @GetMapping("/{id}")
    public InputProduct getOne(@PathVariable Integer id) {
        return inputProductService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody InputProductDto inputProductDto, @PathVariable Integer id) {
        return inputProductService.edit(inputProductDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputProductService.delete(id);
    }
}
