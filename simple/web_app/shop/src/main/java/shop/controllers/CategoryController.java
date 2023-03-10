package shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.dto.category.CategoryCreateDTO;
import shop.dto.category.CategoryItemDTO;
import shop.entities.CategoryEntity;
import shop.mapper.CategoryMapper;
import shop.repositories.CategoryRepository;
import shop.storage.StorageService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index(){
        var list = categoryRepository.findAll();
        var model = categoryMapper.CategoryItemByCategories(list);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryItemDTO> create(@ModelAttribute CategoryCreateDTO model) {
        //зберігаємо назву завантаженого зображення
        var fileName = storageService.saveMultipartFile(model.getFile());
        CategoryEntity category = categoryMapper.CategoryByCreateDTO(model);
        //встановлюємо зображення до категорії
        category.setImage(fileName);
        //зберігаємо нову щойно створену категорію
        categoryRepository.save(category);
        var result = categoryMapper.CategoryItemByCategory(category);
        //повертаємо відповідь призавершенні збереження категорії
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
//    @PutMapping("put/{id}")
//    public ResponseEntity<CategoryItemDTO> put(@RequestBody CategoryCreateDTO model, int id) {
//        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
//        if (!optionalCategory.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        CategoryEntity category = optionalCategory.get();
//        category.setName(model.getName());
//        category.setDescription(model.getDescription());
//        category.setImage(storageService.save(model.getBase64()));
//        categoryRepository.save(category);
//        CategoryItemDTO result = categoryMapper.CategoryItemByCategory(category);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
    @DeleteMapping("delete_category/{id}")
    public String delete(@PathVariable int id){
        categoryRepository.deleteById((id));
        return "Category was deleted";
    }


}
