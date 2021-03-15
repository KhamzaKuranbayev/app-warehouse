package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.service.CurrencyService;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public Result save(@RequestBody Currency currency) {
        return currencyService.save(currency);
    }

    @GetMapping
    public List<Currency> getAll(){
        return currencyService.findAll();
    }

    @GetMapping("/{id}")
    public Currency getOne(@PathVariable Integer id) {
        return currencyService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody Currency currency, @PathVariable Integer id) {
        return currencyService.edit(currency, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return currencyService.delete(id);
    }

}
