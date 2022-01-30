package openLegacyStore.openLegacyStore.Repositories;

import openLegacyStore.openLegacyStore.Beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    boolean existsProductByNameAndId (String email, int id);
    boolean existsProductyByName (String name);
    Product findById(int id);


}
