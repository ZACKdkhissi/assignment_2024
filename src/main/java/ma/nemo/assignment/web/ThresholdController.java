package ma.nemo.assignment.web;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Threshold;
import ma.nemo.assignment.dto.ThresholdDto;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ThresholdController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ThresholdRepository thresholdRepository;

    @PostMapping("/set-threshold")
    public ResponseEntity<Long> setThreshold(@RequestBody ThresholdDto thresholdDto)
            throws ProductNotFound{

        Product existingProduct = productRepository.findByProductCode(thresholdDto.getProductCode());

        if (existingProduct == null) {
            throw new ProductNotFound("Le produit n'existe pas.");
        }

        Threshold threshold = new Threshold();
        threshold.setProductCode(thresholdDto.getProductCode());
        threshold.setProduct(existingProduct);
        threshold.setThresholdQuantity(thresholdDto.getThresholdQuantity());

        Threshold sevedThreshold = thresholdRepository.save(threshold);



        return new ResponseEntity<>(sevedThreshold.getThresholdId(), HttpStatus.CREATED);

    }

    @GetMapping("/threshold-alerts")
    public ResponseEntity<List<Product>> getProductsBelowThreshold() {
        List<Product> products = new ArrayList<>();

        List<Threshold> thresholds = thresholdRepository.findAll();
        for (Threshold threshold : thresholds) {
            if (threshold.getProduct().getQuantityInStock() < threshold.getThresholdQuantity()) {
                products.add(threshold.getProduct());
            }
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
