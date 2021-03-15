package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.response.ResponseProducts;

import java.sql.Timestamp;

@Service
public interface DashboardService {

     ResponseProducts getAllInputProductsByDate(Timestamp date);

     ResponseProducts getAllOutputProductsByDate(Timestamp date);

}
