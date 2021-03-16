package uz.pdp.appwarehouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTotal {

    private String totalAmount;
    private String totalPrice;

}
