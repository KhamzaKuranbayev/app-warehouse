package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.CategoryDto;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Result save(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new Result("Bunday nomli categoriya mavjud", false);

        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday id li parent category topilmadi!", false);
            category.setParentCategory(optionalCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category saqlandi!", true);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public Result edit(CategoryDto categoryDto, Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            optionalCategory.get().setName(categoryDto.getName());

            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory1.isPresent())
                return new Result("Bunday id li parent category topilmadi!", false);

            categoryRepository.save(optionalCategory.get());
            return new Result("Categoriya tahrirlandi!", true);
        }

        return new Result("Bunday id lik categoriya topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return new Result("Muvaffaqiyatli o'chirildi!", true);
        }
        return new Result("Bunday categoriya topilmadi!", false);
    }
}
