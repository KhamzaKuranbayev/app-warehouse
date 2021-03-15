package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.response.ResponseProducts;
import uz.pdp.appwarehouse.dtos.response.ProductInfo;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardServiceImpl implements DashboardService {

    final InputProductRepository inputProductRepository;
    final ProductRepository productRepository;

    public DashboardServiceImpl(InputProductRepository inputProductRepository,
                                ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseProducts getAllInputProductsByDate(Timestamp date) {
        ResponseProducts response = new ResponseProducts();

        List<ProductInfo> productInfoList = new ArrayList<>();

        List<InputProduct> inputProductsByInputDateNative = inputProductRepository.getInputProductsByInputDateNative(date);

        Double totalPrice = 0.0;
        for (InputProduct inputProduct : inputProductsByInputDateNative) {
            Optional<Product> optionalProduct = productRepository.findById(inputProduct.getProduct().getId());
            optionalProduct.ifPresent(product -> productInfoList.add(new ProductInfo(product.getName(), inputProduct.getAmount())));

            totalPrice += inputProduct.getPrice();
        }

        response.setProductInfoList(productInfoList);
        response.setTotalPrice(totalPrice);

        return response;
    }

    @Override
    public ResponseProducts getAllOutputProductsByDate(Timestamp date) {
        ResponseProducts response = new ResponseProducts();





        return null;
    }
}
