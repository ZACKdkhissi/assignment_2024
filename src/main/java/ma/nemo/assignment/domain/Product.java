package ma.nemo.assignment.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import lombok.ToString;

@Entity
@Table(name = "Products")
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(unique = true, nullable = false)
  private String productCode;

  @Column(nullable = false)
  private String productName;

  private String description;

  private Double unitPrice;

  private Integer quantityInStock;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;

  private Date expirationDate;

  //////////
  @OneToMany(mappedBy = "product")
  private List<Sale> sales;

  @OneToMany(mappedBy = "product")
  private List<Supply> supplies;

  @OneToMany(mappedBy = "product")
  private List<TransactionHistory> transactionHistoriesProduct;

  @OneToMany(mappedBy = "product")
  private List<Return> returns;

  @OneToOne(mappedBy = "product")
  private Threshold threshold;
  /////////

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public void setQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Date modificationDate) {
    this.modificationDate = modificationDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  // Getters, setters, etc.
}