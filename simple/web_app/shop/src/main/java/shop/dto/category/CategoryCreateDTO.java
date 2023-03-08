package shop.dto.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryCreateDTO {
    private String Name;
    //private String base64;
    private String description;
    private MultipartFile file;
}
