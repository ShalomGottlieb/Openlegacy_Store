package openLegacyStore.openLegacyStore.CLR;

import lombok.RequiredArgsConstructor;
import openLegacyStore.openLegacyStore.Beans.Category;
import openLegacyStore.openLegacyStore.Beans.Product;
import openLegacyStore.openLegacyStore.Service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class MokeData implements CommandLineRunner {
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        Product product1 =Product.builder()
                .name("bamba")
                .amount(100)
                .categoryID(Category.FOOD)
                .price(7.9)
                .build();
        Product product2 =Product.builder()
                .name("cigarette")
                .amount(50)
                .categoryID(Category.OTHER)
                .price(25)
                .build();
        Product product3 =Product.builder()
                .name("spoons")
                .amount(400)
                .categoryID(Category.HOME)
                .price(9.9)
                .build();
        Product product4 =Product.builder()
                .name("blow heater")
                .amount(10)
                .categoryID(Category.ELECTRICITY)
                .price(99.9)
                .build();
        productService.addNewProduct(product1);
        productService.addNewProduct(product2);
        productService.addNewProduct(product3);
        productService.addNewProduct(product4);

        System.out.println("4 products created");

        }
    }

