package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.OutputProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductServiceImpl implements OutputProductService {

    final OutputProductRepository outputProductRepository;
    final ProductRepository productRepository;
    final OutputRepository outputRepository;

    public OutputProductServiceImpl(OutputProductRepository outputProductRepository,
                                    ProductRepository productRepository, OutputRepository outputRepository) {
        this.outputProductRepository = outputProductRepository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
    }

    @Override
    public Result save(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot topilmadi!", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getInputId());
        if (!optionalOutput.isPresent())
            return new Result("Bunday chiqim topilmadi!", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("Ma'lumot saqlandi!", true);
    }

    @Override
    public List<OutputProduct> findAll() {
        return outputProductRepository.findAll();
    }

    @Override
    public OutputProduct findById(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    @Override
    public Result edit(OutputProductDto outputProductDto, Integer id) {

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isPresent()){

            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
            if (!optionalProduct.isPresent())
                return new Result("Bunday maxsulot topilmadi!", false);

            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getInputId());
            if (!optionalOutput.isPresent())
                return new Result("Bunday chiqim topilmadi!", false);

            optionalOutputProduct.get().setAmount(outputProductDto.getAmount());
            optionalOutputProduct.get().setPrice(outputProductDto.getPrice());
            optionalOutputProduct.get().setProduct(optionalProduct.get());
            optionalOutputProduct.get().setOutput(optionalOutput.get());

            outputProductRepository.save(optionalOutputProduct.get());

            return new Result("Ma'lumot o'zgartirildi!", true);
        }
        return new Result("Bunday ma'lumot topilmadi!", false);

    }

    @Override
    public Result delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isPresent()){
            outputProductRepository.deleteById(id);
            return new Result("Ma'lumot o'chirildi!", true);
        }
        return new Result("Bunday ma'lumot topilmadi!", false);
    }
}
