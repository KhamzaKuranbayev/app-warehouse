package uz.pdp.appwarehouse.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAttachment {

    private String name;

    private long size;

    private String contentType;

    private byte[] mainContent;
}
