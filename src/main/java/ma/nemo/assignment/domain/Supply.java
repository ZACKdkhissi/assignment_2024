package ma.nemo.assignment.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Supplies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long supplyId;

  @ManyToOne
  @JoinColumn(name = "productId")
  private Product product;

  private Integer quantity;

  @Temporal(TemporalType.TIMESTAMP)
  private Date supplyDate;

  private Date expirationDate;



}
