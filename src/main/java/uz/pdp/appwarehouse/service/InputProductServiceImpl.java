package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.InputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductServiceImpl implements InputProductService {

    final InputProductRepository inputProductRepository;
    final ProductRepository productRepository;
    final InputRepository inputRepository;

    public InputProductServiceImpl(InputProductRepository inputProductRepository,
                                   ProductRepository productRepository, InputRepository inputRepository) {
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
        this.inputRepository = inputRepository;
    }

    @Override
    public Result save(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot topilmadi!", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim topilmadi!", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Ma'lumot saqlandi!", true);
    }

    @Override
    public List<InputProduct> findAll() {
        return inputProductRepository.findAll();
    }

    @Override
    public InputProduct findById(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    @Override
    public Result edit(InputProductDto inputProductDto, Integer id) {

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()){

            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
            if (!optionalProduct.isPresent())
                return new Result("Bunday maxsulot topilmadi!", false);

            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
            if (!optionalInput.isPresent())
                return new Result("Bunday kirim topilmadi!", false);

            optionalInputProduct.get().setAmount(inputProductDto.getAmount());
            optionalInputProduct.get().setPrice(inputProductDto.getPrice());
            optionalInputProduct.get().setExpireDate(inputProductDto.getExpireDate());
            optionalInputProduct.get().setProduct(optionalProduct.get());
            optionalInputProduct.get().setInput(optionalInput.get());

            inputProductRepository.save(optionalInputProduct.get());

            return new Result("Ma'lumot o'zgartirildi!", true);
        }
        return new Result("Bunday ma'lumot topilmadi!", false);

    }

    @Override
    public Result delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()){
            inputProductRepository.deleteById(id);
            return new Result("Ma'lumot o'chirildi!", true);
        }
        return new Result("Bunday ma'lumot topilmadi!", false);
    }
}
