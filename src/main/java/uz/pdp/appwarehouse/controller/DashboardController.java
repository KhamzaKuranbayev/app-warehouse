package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.dtos.response.ResponseProducts;
import uz.pdp.appwarehouse.service.DashboardService;

import java.sql.Timestamp;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Kunlik kirim bo’lgan mahsulotlar (qiymati, umumiy summasi)
    @GetMapping
    public ResponseProducts getInputProductsByDate(@RequestParam("date") Timestamp date){
        return dashboardService.getAllInputProductsByDate(date);
    }

    // Kunlik eng ko’p chiqim qilingan mahsulotlar
    /*@GetMapping
    public ResponseProducts getOutputProductsByDate(@RequestParam("date") Timestamp date){

    }*/

}
