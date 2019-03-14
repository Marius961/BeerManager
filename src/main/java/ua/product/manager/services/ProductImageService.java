package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProductImageService {

    @Value(("${upload.path}"))
    private String uploadPath;

    public String saveImage(MultipartFile file) throws IOException {
        if(file != null) {
            System.out.println(file.getSize());
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + '/' + resultFileName));
            System.out.println(resultFileName);
            return resultFileName;
        }
        return "";
    }
}
