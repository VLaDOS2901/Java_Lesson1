package shop.storage;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.UID;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try{
            if(!Files.exists(rootLocation))
                Files.createDirectories(rootLocation);
        }catch (IOException ex){
            throw new StorageException("Помилка створення папки", ex);
        }
    }
    @Override
    public Resource loasAsResource(String fileName){
        try{
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()||resource.isReadable())
                return resource;
            throw new StorageException("Проблема при роботі з файлом" + fileName);
        }catch (MalformedURLException e){
            throw new StorageException("File not found");
        }
    }

    @Override
    public String save(String base64) {
        try{
            if(base64.isEmpty()){
                throw new StorageException("Порожній base64");
            }
            UUID uuid = UUID.randomUUID();
            String randomFileName = uuid.toString()+".jpg";
            String [] charArray = base64.split(",");
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = new byte[0];
            bytes = decoder.decode(charArray[1]);
            String folder = rootLocation.toString()+"/"+randomFileName;
            new FileOutputStream(folder).write(bytes);
            return randomFileName;
        }catch (IOException e){
            throw new StorageException("Проблема при збереженні та перетворенні base64", e);
        }
    }
}
