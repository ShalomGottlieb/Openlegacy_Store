package openLegacyStore.openLegacyStore.Controller;

import lombok.RequiredArgsConstructor;
import openLegacyStore.openLegacyStore.Beans.Product;
import openLegacyStore.openLegacyStore.Exceptions.AlreadyExistsException;
import openLegacyStore.openLegacyStore.Exceptions.IllegalRequestException;
import openLegacyStore.openLegacyStore.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class myTest {
    private final ProductService productService;

    @PostMapping("withdrawProduct")
    private ResponseEntity<?> withdrawProduct(@RequestBody Product product, int amount) throws Exception {
        try {
            productService.withdrawProduct(product, amount);
            return ResponseEntity.accepted()
                    .body("successfully purchased");
        } catch (IllegalRequestException e) {
            throw new IllegalRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) throws Exception {
        try {
            productService.addProduct(product);
            return ResponseEntity.accepted()
                    .body("created successfully");
        } catch (AlreadyExistsException e) {
            throw new AlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("updateProduct")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws Exception {
        try {
            productService.updateProduct(product);
            return ResponseEntity.accepted()
                    .body("updated successfully");
        } catch (AlreadyExistsException e) {
            throw new AlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("deleteProduct/:{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) throws Exception {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.accepted()
                    .body("deleted successfully");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("getAllProducts")
    public ResponseEntity<?> getAllProducts() throws Exception {
        try {
            return ResponseEntity.ok()
                    .body(productService.getAllProducts());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}