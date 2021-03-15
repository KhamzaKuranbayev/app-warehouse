package uz.pdp.appwarehouse.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProducts {

    private List<ProductInfo> productInfoList;
    private Double totalPrice;


}
