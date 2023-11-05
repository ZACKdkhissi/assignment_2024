package ma.nemo.assignment.web;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/expiry-alerts")
public class ExpiryAlertsController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getExpiryAlerts() {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationLimit = currentDate.plusDays(30);

       Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);
       Date sqlExpirationLimit = java.sql.Date.valueOf(expirationLimit);

        List<Product> expiryAlerts = productRepository.findExpiryAlerts(sqlCurrentDate, sqlExpirationLimit);

        if (expiryAlerts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(expiryAlerts, HttpStatus.OK);
        }
    }

}
