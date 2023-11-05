package ma.nemo.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SupplyDto {
        private String productCode;
        private int quantity;
        private Date expirationDate;

}
