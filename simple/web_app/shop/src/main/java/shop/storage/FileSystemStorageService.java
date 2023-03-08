package shop.storage;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    //Повертає URI до файлу
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
    //Метод, який конвертує base64 у зображення і зберігає його в різних розмірах
    @Override
    public String save(String base64) {
        try {
            if(base64.isEmpty()) {
                throw new StorageException("Пустий base64");
            }
            UUID uuid = UUID.randomUUID();
            String [] charArray = base64.split(",");
            String extension;
            switch(charArray[0]) {
                case "data:image/png;base64":
                    extension="png";
                    break;
                default:
                    extension="jpg";
                    break;
            }
            String randomFileName = uuid.toString()+"."+extension;
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = new byte[0];
            bytes = decoder.decode(charArray[1]);
            int [] imageSize = {32,150, 300, 600, 1200};
            try(var byteStream = new ByteArrayInputStream(bytes)) {
                var image = ImageIO.read(byteStream);
                //цикл, який зберігає одне зображення в різних розмірах
                for(int size : imageSize) {
                    String fileSaveItem = rootLocation.toString()+"/"+size+"_"+randomFileName;
                    BufferedImage newImg = ImageUtils.resizeImage(image,
                            extension=="jpg" ? ImageUtils.IMAGE_JPEG: ImageUtils.IMAGE_PNG, size, size);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(newImg, extension, byteArrayOutputStream);
                    byte [] newBytes = byteArrayOutputStream.toByteArray();
                    FileOutputStream out = new FileOutputStream(fileSaveItem);
                    out.write(newBytes);
                    out.close();
                }
            } catch(IOException e) {
                throw new StorageException("Проблема перетворення фото", e);
            }
            return randomFileName;

        } catch(StorageException e) {
            throw new StorageException("Проблема при збережні та перетворені base64",e);
        }
    }

    //Метод, який зберігає завантажену фотографію в різних розмірах
    @Override
    public String saveMultipartFile(MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            String extension="jpg";
            String randomFileName = uuid.toString()+"."+extension;
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = file.getBytes();
            int [] imageSize = {32,150, 300, 600, 1200};
            try(var byteStream = new ByteArrayInputStream(bytes)) {
                var image = ImageIO.read(byteStream);
                //цикл, який зберігає одне зображення в різних розмірах
                for(int size : imageSize) {
                    String fileSaveItem = rootLocation.toString()+"/"+size+"_"+randomFileName;
                    BufferedImage newImg = ImageUtils.resizeImage(image,
                            extension=="jpg" ? ImageUtils.IMAGE_JPEG: ImageUtils.IMAGE_PNG, size, size);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(newImg, extension, byteArrayOutputStream);
                    byte [] newBytes = byteArrayOutputStream.toByteArray();
                    FileOutputStream out = new FileOutputStream(fileSaveItem);
                    out.write(newBytes);
                    out.close();
                }
            } catch(IOException e) {
                throw new StorageException("Проблема перетворення фото", e);
            }
            return randomFileName;

        } catch(Exception e) {
            throw new StorageException("Проблема при збережні та перетворені base64",e);
        }
    }

    @Override
    public void removeFile(String name) {

    }

    @Override
    public Path load(String filename) {
        return null;
    }
}
