package uz.pdp.appwarehouse.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.DateUtil;
import uz.pdp.appwarehouse.constants.ExpireStatus;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.repository.InputProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class ExpireCheckService {

    final InputProductRepository inputProductRepository;

    public ExpireCheckService(InputProductRepository inputProductRepository) {
        this.inputProductRepository = inputProductRepository;
    }

    @Scheduled(cron = "0 9 * * ?")
    void checkWarningStatus(){

        Date date = DateUtil.getDateFromFuture(10);
        List<InputProduct> inputProductList = inputProductRepository.getWarningProducts(date);

        for (InputProduct inputProduct: inputProductList) {
            inputProduct.setExpireStatus(ExpireStatus.WARNING);
            inputProductRepository.save(inputProduct);
        }

    }


}
