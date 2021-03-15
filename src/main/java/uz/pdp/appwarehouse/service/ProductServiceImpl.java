package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.ProductDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final MeasurementRepository measurementRepository;
    final AttachmentRepository attachmentRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              MeasurementRepository measurementRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Result save(ProductDto productDto) {

        if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId()))
            return new Result("Bunday maxsulot ushbu kategoriyada bor!", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas!", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'lchov birligi mavjud!", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas!", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(generateUUIDCode());    // todo generatsiya qilish kerak

        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());

        return new Result("Maxsulot saqlandi!", true);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Result edit(ProductDto productDto, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(productDto.getName());

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday kategoriya mavjud emas!", false);

            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
            if (!optionalMeasurement.isPresent())
                return new Result("Bunday o'lchov birligi mavjud!", false);

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (!optionalAttachment.isPresent())
                return new Result("Bunday rasm mavjud emas!", false);

            productRepository.save(optionalProduct.get());
            return new Result("Maxsulot ma'lumotlari tahrirlandi!", true);
        }
        return new Result("Bunday id li maxsulot topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return new Result("Maxsulot o'chirildi!", true);
        }
        return new Result("Bunday id li maxsulot topilmadi!", false);
    }

    public static String generateUUIDCode() {
        return UUID.randomUUID().toString();
    }

}
