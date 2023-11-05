package ma.nemo.assignment.web.common;

import ma.nemo.assignment.exceptions.ProductAlreadyExists;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Produit non existant", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Produit existe déjà", null, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<String> handleProductValidationException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Validation du produit échouée", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Validation du user échouée", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }




}
