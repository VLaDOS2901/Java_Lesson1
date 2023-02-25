package shop.storage;


import org.springframework.core.io.Resource;

public interface StorageService {
    void init();
    Resource loasAsResource(String fileName);
    String save(String base64);
}
