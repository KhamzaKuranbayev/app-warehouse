package uz.pdp.appwarehouse.dtos;

import lombok.Data;

@Data
public class OutputProductDto {

    private Integer productId;
    private Double amount;
    private Double price;
    private Integer inputId;
}
