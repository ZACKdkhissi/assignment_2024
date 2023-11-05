package ma.nemo.assignment.web;


import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Sale;

import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.User;
import ma.nemo.assignment.domain.util.EventType;
import ma.nemo.assignment.dto.SaleDto;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.exceptions.UserNotFound;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.repository.SaleRepository;
import ma.nemo.assignment.repository.TransactionHistoryRepository;
import ma.nemo.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @PostMapping
    public ResponseEntity<Long> addSale(@RequestBody SaleDto saledto) throws ProductValidationException, ProductNotFound, UserNotFound {

        Product product = productRepository.findByProductCode(saledto.getProductCode());

        if (product == null) {
            throw new ProductNotFound("Le produit n'existe pas.");
        }

        if (product.getQuantityInStock() < saledto.getQuantity()) {
            throw new ProductValidationException("La quantité en stock est insuffisante pour cette vente.");
        }

        Optional<User> userOptional = userRepository.findById(saledto.getUserId());

        if (!userOptional.isPresent()) {
            throw new UserNotFound("L'utilisateur n'existe pas.");
        }

        User user = userOptional.get();

        double totalPrice = product.getUnitPrice() * saledto.getQuantity();

        int newQuantityInStock = product.getQuantityInStock() - saledto.getQuantity();
        product.setQuantityInStock(newQuantityInStock);

        Sale sale = new Sale();
        sale.setSoldQuantity(saledto.getQuantity());
        sale.setTotalPrice(totalPrice);
        sale.setProduct(product);
        sale.setSaleDate(new Date());

        sale.setUser(user);

        Sale savedSale = saleRepository.save(sale);

        TransactionHistory transaction = new TransactionHistory();
        transaction.setProduct(product);
        transaction.setQuantity(saledto.getQuantity());
        transaction.setTransactionType(EventType.SALE.getType());
        transaction.setTransactionDate(new Date());
        transaction.setUser(user);

        transactionHistoryRepository.save(transaction);

        return new ResponseEntity<>(savedSale.getSaleId(), HttpStatus.CREATED);
    }

}
