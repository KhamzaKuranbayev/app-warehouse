package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.InputDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping
    public Result save(@RequestBody InputDto inputDto) {
        return inputService.save(inputDto);
    }

    @GetMapping
    public List<Input> getAll(){
        return inputService.findAll();
    }

    @GetMapping("/{id}")
    public Input getOne(@PathVariable Integer id) {
        return inputService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody InputDto inputDto, @PathVariable Integer id) {
        return inputService.edit(inputDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputService.delete(id);
    }
}
