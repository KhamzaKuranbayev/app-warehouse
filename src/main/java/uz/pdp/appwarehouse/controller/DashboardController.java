package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.dtos.DailyTotal;
import uz.pdp.appwarehouse.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Kunlik kirim bo’lgan mahsulotlar (qiymati, umumiy summasi)
    @GetMapping("/input")
    public DailyTotal getInputProductsByDate() {
        return dashboardService.inputProducts();
    }

    // Kunlik eng ko’p chiqim qilingan mahsulotlar
    @GetMapping
    public DailyTotal getOutputProductsByDate(){
        return dashboardService.outputProducts();
    }

}
