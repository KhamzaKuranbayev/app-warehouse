package uz.pdp.appwarehouse.dtos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {

    private Timestamp date;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;

}
