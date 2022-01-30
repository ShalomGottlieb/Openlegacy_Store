package openLegacyStore.openLegacyStore.Controller;

import lombok.RequiredArgsConstructor;
import openLegacyStore.openLegacyStore.Beans.Product;
import openLegacyStore.openLegacyStore.Exceptions.AlreadyExistsException;
import openLegacyStore.openLegacyStore.Exceptions.IllegalRequestException;
import openLegacyStore.openLegacyStore.Exceptions.NotFoundException;
import openLegacyStore.openLegacyStore.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StoreController {
    private final ProductService productService;

    //client
    @PostMapping("withdrawProduct")
    private ResponseEntity<?> withdrawProduct(@RequestBody int productId, int amount) throws Exception {
        try {
            productService.withdrawProduct(productId, amount);
            return ResponseEntity.accepted()
                    .body("successfully purchased");
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (IllegalRequestException e) {
            throw new IllegalRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //supplier
    @PostMapping("addProduct")
    public ResponseEntity<?> addAmountProduct(@RequestBody int productId, int amount) throws Exception {
        try {
            productService.addAmountProduct(productId, amount);
            return ResponseEntity.accepted()
                    .body("amount added successfully");
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //admin
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

    @PostMapping("addNewProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product) throws Exception {
        try {
            productService.addNewProduct(product);
            return ResponseEntity.accepted()
                    .body("created successfully");
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
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
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

    @PostMapping("getProductsAlmostFinish")
    public ResponseEntity<?> getProductsAlmostFinish(int amount) throws Exception {
        try {
            return ResponseEntity.ok()
                    .body(productService.getProductsAmountLessThen(amount));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}