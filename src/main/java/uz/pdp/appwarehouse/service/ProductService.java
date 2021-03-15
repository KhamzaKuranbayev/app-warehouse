package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.ProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Product;

import java.util.List;

@Service
public interface ProductService {

    Result save(ProductDto productDto);

    List<Product> findAll();

    Product findById(Integer id);

    Result edit(ProductDto productDto, Integer id);

    Result delete(Integer id);

}
