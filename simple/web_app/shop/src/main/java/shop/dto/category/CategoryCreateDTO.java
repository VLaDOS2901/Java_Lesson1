package shop.dto.category;

import lombok.Data;

@Data
public class CategoryCreateDTO {
    private String Name;
    private String base64;
    private String description;
}
