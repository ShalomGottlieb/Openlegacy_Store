package openLegacyStore.openLegacyStore.Repositories;

import openLegacyStore.openLegacyStore.Beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    boolean existsProductByNameAndId (String email, int id);
    boolean existsProductByName (String name);
    Product findById(int id);
    List<Product> findByAmountLessThan (int amount);


}
