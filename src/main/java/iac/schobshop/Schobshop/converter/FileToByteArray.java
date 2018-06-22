package iac.schobshop.Schobshop.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileToByteArray implements Converter<MultipartFile, Byte[]> {

    @Override
    public Byte[] convert(MultipartFile file) {

        try {
            // getBytes() might throw an exception...
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            return byteObjects;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
