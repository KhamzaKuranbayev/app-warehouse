package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.DailyTotal;

@Service
public interface DashboardService {

     DailyTotal inputProducts();

     DailyTotal outputProducts();

}
