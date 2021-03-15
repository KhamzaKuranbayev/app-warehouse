package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.dtos.response.ResponseAttachment;
import uz.pdp.appwarehouse.entity.Attachment;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface AttachmentService {

    Result upload(MultipartHttpServletRequest request);

    List<Attachment> findAll();

    ResponseAttachment findOne(Integer id, HttpServletResponse response);

    Result edit(MultipartHttpServletRequest request, Integer id);

    Result delete(Integer id);

}
