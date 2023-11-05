package ma.nemo.assignment.repository;

import jakarta.transaction.Transactional;
import ma.nemo.assignment.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TransferRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void ProductRepository_GetAll_ReturnMoreThanOneProduct(){
    Product p1 = new Product();
    p1.setProductCode("ABC117");
    p1.setProductName("Nom du produit 7");
    p1.setDescription("Description du produit 7");
    p1.setUnitPrice(42.99);
    p1.setQuantityInStock(700);
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date expirationDate = dateFormat.parse("2023-12-03");
      p1.setExpirationDate(expirationDate);
    } catch (ParseException e) {
      System.out.println("Erreur de format de date : " + e.getMessage());
    }

    productRepository.save(p1);
    List<Product> listPrd = productRepository.findAll();

    Assertions.assertEquals(6,listPrd.size());
  }

  @Test
  public void findOne() {
  }

  @Test
  public void findAll() {

  }

  @Test
  public void save() {
  }

  @Test
  public void delete() {
  }
}

