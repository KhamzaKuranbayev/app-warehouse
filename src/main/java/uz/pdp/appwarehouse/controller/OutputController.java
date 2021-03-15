package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.OutputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.service.InputService;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public Result save(@RequestBody OutputDto outputDto) {
        return outputService.save(outputDto);
    }

    @GetMapping
    public List<Output> getAll(){
        return outputService.findAll();
    }

    @GetMapping("/{id}")
    public Output getOne(@PathVariable Integer id) {
        return outputService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody OutputDto outputDto, @PathVariable Integer id) {
        return outputService.edit(outputDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputService.delete(id);
    }
}
