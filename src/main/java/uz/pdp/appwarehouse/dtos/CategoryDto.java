package uz.pdp.appwarehouse.dtos;

import lombok.Data;

@Data
public class CategoryDto {

    private String name;

    private Integer parentCategoryId;

}
