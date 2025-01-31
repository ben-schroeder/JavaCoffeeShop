package nl.brianvermeer.workshop.coffee.repository;

import nl.brianvermeer.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        var lowerInput = input.toLowerCase(Locale.ROOT);
        String query = "Select * from Product where lower(description) like :input OR lower(product_name) like :input";
        var resultList = (List<Product>) em.createNativeQuery(query, Product.class)
                .setParameter("input", "%" + lowerInput + "%")
                .getResultList();
        return resultList;
    }

}
