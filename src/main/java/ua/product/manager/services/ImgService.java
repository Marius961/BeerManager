package ua.product.manager.services;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImgService {

    private static final String[] ALLOWED_EXTENSIONS = {".jpeg",".jpg", ".png", ".bmp"};

    @Value(("${images.upload.path}"))
    private String uploadPath;

    public String saveImage(MultipartFile file) throws IOException {
        if(file != null) {
            String name = file.getOriginalFilename();
            if (name != null) {
                File uploadFolder = new File(uploadPath);
                boolean isUploadFolderExists = uploadFolder.exists();

                if (!isUploadFolderExists) {
                    isUploadFolderExists = uploadFolder.mkdir();
                }

                boolean isExstensionValid;
                String extension = name.substring(name.lastIndexOf("."));
                isExstensionValid = isImageExtensionValid(extension);

                if (isExstensionValid && isUploadFolderExists) {
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFileName = uuidFile + "." + file.getOriginalFilename();

                    File convertedFile = convertMultiPartToFile(file);

                    BufferedImage image = ImageIO.read(convertedFile);
                    Files.deleteIfExists(convertedFile.toPath());


                    File compressedImageFile = new File(uploadFolder.getPath().concat('/' + resultFileName));
                    OutputStream os = new FileOutputStream(compressedImageFile);

                    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
                    ImageWriter writer = writers.next();

                    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                    writer.setOutput(ios);

                    ImageWriteParam param = writer.getDefaultWriteParam();

                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(0.07f);
                    writer.write(null, new IIOImage(image, null, null), param);
                    os.close();
                    ios.close();
                    writer.dispose();
                    return resultFileName;
                } else throw new IOException("File extension is not valid. Valid file extensions: " + Arrays.toString(ALLOWED_EXTENSIONS));
            } else throw new InvalidFileNameException(null, "Invalid file name");
        } else throw new FileNotFoundException("Uploaded file not found");
    }

    public void deleteImage(String fileName) throws FileNotFoundException {
        File file = new File(uploadPath + "/" + fileName);
        if (!file.delete()) throw new FileNotFoundException("file with name " + fileName + " not found");
    }

    private boolean isImageExtensionValid(String extension) {
        for (String e : ALLOWED_EXTENSIONS) {
            if (e.equalsIgnoreCase(extension)) return true;
        }
        return false;
    }

    private File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }
}
