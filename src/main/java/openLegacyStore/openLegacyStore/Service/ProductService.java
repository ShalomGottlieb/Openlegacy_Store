package openLegacyStore.openLegacyStore.Service;

import lombok.RequiredArgsConstructor;
import openLegacyStore.openLegacyStore.Beans.Product;
import openLegacyStore.openLegacyStore.Exceptions.AlreadyExistsException;
import openLegacyStore.openLegacyStore.Exceptions.IllegalRequestException;
import openLegacyStore.openLegacyStore.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public void withdrawProduct(Product product, int amount) throws IllegalRequestException {
        if (productRepo.findById(product.getId()).getAmount() <= 0) {
            throw new IllegalRequestException("amount is zero, cant complete purchase");
        }
        if (productRepo.findById(product.getId()).getAmount()-amount < 0) {
            throw new IllegalRequestException("sorry, only " +productRepo.findById(product.getId()).getAmount()+" in stock, cant complete purchase");
        }
        Product product1= productRepo.findById(product.getId());
        product1.setAmount(productRepo.findById(product1.getId()).getAmount() - amount);
        productRepo.saveAndFlush(product1);
        System.out.println("purchase completed");
    }

    public void addProduct(Product product) throws AlreadyExistsException {
        if (productRepo.existsProductyByName(product.getName())) {
            throw new AlreadyExistsException("customer email already exists, try enter a new one");
        } else {
            productRepo.save(product);
            System.out.println("product successfully added!");
        }
    }

    public void updateProduct(Product product) throws AlreadyExistsException {
        if (productRepo.existsProductyByName(product.getName())&&
                !productRepo.existsProductByNameAndId(product.getName(), product.getId())) {
            throw new AlreadyExistsException("product name already exists, try enter a new one");
        } else {
            productRepo.saveAndFlush(product);
            System.out.println("product successfully updated!");
        }
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
        System.out.println("product successfully deleted!");
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }



}

