package ma.nemo.assignment.repository;

import jakarta.transaction.Transactional;
import ma.nemo.assignment.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TransferRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SupplyRepository supplyRepository;

  @Autowired
  private SaleRepository saleRepository;

  @Autowired
  private ReturnRapository returnRapository;

  @Autowired
  private ThresholdRepository thresholdRepository;

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
  public void saveSupplyRepositoryTest() {
    Supply supply = new Supply();
    supply.setQuantity(200);

    Supply savedSupply = supplyRepository.save(supply);

    Assertions.assertEquals(31,savedSupply.getSupplyId());
  }

  @Test
  public void saveSaleRepositoryTest() {
    Sale sale = new Sale();

    Sale savedSale = saleRepository.save(sale);

    Assertions.assertEquals(9,savedSale.getSaleId());
  }

  @Test
  public void saveReturnRepositoryTest() {
    Return retrn = new Return();

    Return savedReturn = returnRapository.save(retrn);

    Assertions.assertEquals(7,savedReturn.getReturnId());
  }

  @Test
  public void testFindExpiryAlerts() {
    Product product1 = new Product();
    product1.setProductCode("E001");
    product1.setProductName("prd1");
    product1.setQuantityInStock(100);
    product1.setExpirationDate(new Date());


    Product product2 = new Product();
    product2.setProductCode("E002");
    product2.setProductName("prd2");
    product2.setQuantityInStock(100);
    product2.setExpirationDate(new Date(System.currentTimeMillis() + 2 * 86400000));

    productRepository.save(product1);
    productRepository.save(product2);

    Date currentDate = new Date();
    Date expirationLimit = new Date(currentDate.getTime() + 5 * 86400000);
    List<Product> expiryAlerts = productRepository.findExpiryAlerts(currentDate, expirationLimit);

    Assertions.assertEquals(2, expiryAlerts.size());
  }

  @Test
  public void findAllThresholdRepository(){
    List<Threshold> thresholds = thresholdRepository.findAll();
    Assertions.assertEquals(2,thresholds.size());
  }
}

