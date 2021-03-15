package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.ProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Result save(@RequestBody ProductDto productDto){
        return productService.save(productDto);
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Integer id){
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        return productService.edit(productDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return productService.delete(id);
    }


}
