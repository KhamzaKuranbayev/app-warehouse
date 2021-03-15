package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dtos.CategoryDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result save(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return categoryService.edit(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
}
