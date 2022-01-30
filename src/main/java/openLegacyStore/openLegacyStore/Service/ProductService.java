package openLegacyStore.openLegacyStore.Service;

import lombok.RequiredArgsConstructor;
import openLegacyStore.openLegacyStore.Beans.Product;
import openLegacyStore.openLegacyStore.Exceptions.AlreadyExistsException;
import openLegacyStore.openLegacyStore.Exceptions.IllegalRequestException;
import openLegacyStore.openLegacyStore.Exceptions.NotFoundException;
import openLegacyStore.openLegacyStore.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public void withdrawProduct(int productID, int amount) throws IllegalRequestException, NotFoundException {
        if (productRepo.findById(productID)==null) {
            throw new NotFoundException("product not found");
        }
        if (productRepo.findById(productID).getAmount() <= 0) {
            throw new IllegalRequestException("amount is zero, cant complete purchase");
        }
        if (productRepo.findById(productID).getAmount()-amount < 0) {
            throw new IllegalRequestException("sorry, only " +productRepo.findById(productID).getAmount()+" in stock, cant complete purchase");
        }
        Product product1= productRepo.findById(productID);
        product1.setAmount(productRepo.findById(product1.getId()).getAmount() - amount);
        productRepo.saveAndFlush(product1);
        System.out.println("purchase completed");
    }

    public void addAmountProduct(int productID, int amount) throws NotFoundException {
        if (productRepo.findById(productID)==null) {
            throw new NotFoundException("product not found");
        }
        Product product1= productRepo.findById(productID);
        product1.setAmount(productRepo.findById(product1.getId()).getAmount() + amount);
        productRepo.saveAndFlush(product1);
        System.out.println("amount added successfully");
    }

    public void addNewProduct(Product product) throws AlreadyExistsException {
        if (productRepo.existsProductByName(product.getName())) {
            throw new AlreadyExistsException("customer email already exists, try enter a new one");
        } else {
            productRepo.save(product);
            System.out.println("product successfully added!");
        }
    }

    public void updateProduct(Product product) throws AlreadyExistsException {
        if (productRepo.existsProductByName(product.getName())&&
                !productRepo.existsProductByNameAndId(product.getName(), product.getId())) {
            throw new AlreadyExistsException("product name already exists, try enter a new one");
        } else {
            productRepo.saveAndFlush(product);
            System.out.println("product successfully updated!");
        }
    }

    public void deleteProduct(int id) throws NotFoundException {
        if (productRepo.findById(id)==null) {
            throw new NotFoundException("product not found");
        }
        productRepo.deleteById(id);
        System.out.println("product successfully deleted!");
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getProductsAmountLessThen(int amount) throws NotFoundException {
        if (productRepo.findByAmountLessThan(amount)==null) {
            throw new NotFoundException("product not found");
        }
        return productRepo.findByAmountLessThan(amount);
    }


}

