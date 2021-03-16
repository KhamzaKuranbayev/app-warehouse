package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.DateUtil;
import uz.pdp.appwarehouse.dtos.DailyTotal;
import uz.pdp.appwarehouse.repository.InputProductRepository;

import java.util.Date;

@Service
public class DashboardServiceImpl implements DashboardService {

    final InputProductRepository inputProductRepository;

    public DashboardServiceImpl(InputProductRepository inputProductRepository) {
        this.inputProductRepository = inputProductRepository;
    }

    @Override
    public DailyTotal inputProducts() {
        Date startOfDay = DateUtil.atStartOfDay(new Date());
        return inputProductRepository.findDailyInput(startOfDay);
    }

    @Override
    public DailyTotal outputProducts() {
        Date startOfDay = DateUtil.atStartOfDay(new Date());
        return inputProductRepository.findDailyOutput(startOfDay);
    }
}
