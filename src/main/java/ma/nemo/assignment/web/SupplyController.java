package ma.nemo.assignment.web;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Supply;
import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.util.EventType;
import ma.nemo.assignment.dto.SupplyDto;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.repository.SupplyRepository;
import ma.nemo.assignment.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/supply")
public class SupplyController {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


    @PostMapping
    public ResponseEntity<Long> addSupply(@RequestBody SupplyDto supplyDto)
            throws ProductValidationException, ProductNotFound {

        Product existingProduct = productRepository.findByProductCode(supplyDto.getProductCode());

        if (existingProduct == null) {
            throw new ProductNotFound("Le produit associé à cet approvisionnement n'existe pas.");
        }

        if (supplyDto.getQuantity() <= 0 || supplyDto.getQuantity() > 500) {
            throw new ProductValidationException("La quantité d'approvisionnement n'est pas valide.");
        }

        Supply supply = new Supply();
        supply.setProduct(existingProduct);
        supply.setQuantity(supplyDto.getQuantity());
        supply.setExpirationDate(supplyDto.getExpirationDate());
        supply.setSupplyDate(new Date());


        int newQuantityInStock = existingProduct.getQuantityInStock() + supplyDto.getQuantity();
        existingProduct.setQuantityInStock(newQuantityInStock);

        Supply savedSupply = supplyRepository.save(supply);

        TransactionHistory transaction = new TransactionHistory();
        transaction.setProduct(existingProduct);
        transaction.setQuantity(supplyDto.getQuantity());
        transaction.setTransactionType(EventType.APPROVISIONNEMENT.getType());
        transaction.setTransactionDate(new Date());

        transactionHistoryRepository.save(transaction);

        return new ResponseEntity<>(savedSupply.getSupplyId(), HttpStatus.CREATED);
    }
}