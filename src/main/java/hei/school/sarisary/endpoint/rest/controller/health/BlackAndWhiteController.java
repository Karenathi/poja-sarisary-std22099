package hei.school.sarisary.endpoint.rest.controller.health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import hei.school.sarisary.service.event.BlackAndWhiteService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/black-and-white") // Utilisation d'une annotation de niveau de classe pour le mapping de l'URL
public class BlackAndWhiteController {

    private final BlackAndWhiteService blackAndWhiteService;

    @Autowired
    public BlackAndWhiteController(BlackAndWhiteService blackAndWhiteService) {
        this.blackAndWhiteService = blackAndWhiteService;
    }

    @PutMapping("/{id}") // Utilisation de l'annotation @PutMapping
    public ResponseEntity<Void> uploadImage(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {
        blackAndWhiteService.processImage(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}") // Utilisation de l'annotation @GetMapping
    public ResponseEntity<Map<String, String>> getImageUrls(@PathVariable String id) {
        Map<String, String> imageUrls = blackAndWhiteService.getImageUrls(id);
        if (imageUrls != null && !imageUrls.isEmpty()) { // Vérification de la non-nullité et de la non-vacuité de la map
            return ResponseEntity.ok(imageUrls);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
