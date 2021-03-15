package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.CategoryDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Category;
import java.util.List;

@Service
public interface CategoryService {

    Result save(CategoryDto categoryDto);               // create

    List<Category> findAll();                           // read

    Category findById(Integer id);

    Result edit(CategoryDto categoryDto, Integer id);   // update

    Result delete(Integer id);                          // delete

}
