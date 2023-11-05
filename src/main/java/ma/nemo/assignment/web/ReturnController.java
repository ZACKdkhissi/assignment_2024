package ma.nemo.assignment.web;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Return;
import ma.nemo.assignment.dto.ReturnDto;
import ma.nemo.assignment.dto.SaleDto;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.exceptions.UserNotFound;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.repository.ReturnRapository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/return")
public class ReturnController {
    @Autowired
    private ReturnRapository returnRapository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Long> handleProductReturn(@RequestBody ReturnDto returnDto) throws ProductNotFound {
        Product produit = productRepository.findByProductCode(returnDto.getProductCode());

        if (produit == null) {
            throw new ProductNotFound("Le produit n'existe pas.");

        }

        int newQuantityInStock = produit.getQuantityInStock() + returnDto.getQuantity();
        produit.setQuantityInStock(newQuantityInStock);

        Return returnEntity = new Return();
        returnEntity.setProduct(produit);
        returnEntity.setQuantity(returnDto.getQuantity());
        returnEntity.setReasonForReturn(returnDto.getReasonForReturn());

        Return savedReturn = returnRapository.save(returnEntity);

        return new ResponseEntity<>(savedReturn.getReturnId(), HttpStatus.CREATED);
    }

    }
