package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.dtos.response.ResponseAttachment;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }

    @GetMapping
    public List<Attachment> getAll() {
        return attachmentService.findAll();
    }

    @GetMapping("/download/{id}")
    public ResponseAttachment getOne(@PathVariable Integer id, HttpServletResponse response) {
        return attachmentService.findOne(id, response);
    }

    @PutMapping("/{id}")
    public Result edit(MultipartHttpServletRequest request, @PathVariable Integer id) {
        return attachmentService.edit(request, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return attachmentService.delete(id);
    }

}
