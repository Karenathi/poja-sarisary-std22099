package hei.school.sarisary.service.event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlackAndWhiteService {

    private final Map<String, String> imageUrls = new HashMap<>();

    public void processImage(String id, MultipartFile file) throws IOException {
        String transformedUrl = "https://transformed.url";
        imageUrls.put(id, transformedUrl);
    }
    public Map<String, String> getImageUrls(String id) {
        if (imageUrls.containsKey(id)) {
            String transformedUrl = imageUrls.get(id);
            String originalUrl = "https://original.url"; // Récupérer cette URL à partir du stockage (S3)
            Map<String, String> response = new HashMap<>();
            response.put("original_url", originalUrl);
            response.put("transformed_url", transformedUrl);
            return response;
        } else {
            return null;
        }
    }
}

