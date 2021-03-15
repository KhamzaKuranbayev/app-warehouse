package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public Result save(@RequestBody Measurement measurement) {
        return measurementService.save(measurement);
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.findAll();
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id) {
        return measurementService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody Measurement measurement, @PathVariable Integer id) {
        return measurementService.edit(measurement, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return measurementService.delete(id);
    }
}
