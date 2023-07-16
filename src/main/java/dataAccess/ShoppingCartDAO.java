package dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.ShoppingCart;

@Repository
public interface ShoppingCartDAO extends MongoRepository<ShoppingCart, String>{

}
