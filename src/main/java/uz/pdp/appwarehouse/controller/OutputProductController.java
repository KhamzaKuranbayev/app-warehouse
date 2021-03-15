package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.OutputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    final OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping
    public Result save(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.save(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getAll() {
        return outputProductService.findAll();
    }

    @GetMapping("/{id}")
    public OutputProduct getOne(@PathVariable Integer id) {
        return outputProductService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody OutputProductDto outputProductDto, @PathVariable Integer id) {
        return outputProductService.edit(outputProductDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputProductService.delete(id);
    }
}
