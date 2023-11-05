package ma.nemo.assignment.web;



import jakarta.transaction.Transactional;
import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Supply;
import ma.nemo.assignment.dto.SupplyDto;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.repository.SupplyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;



@WebMvcTest(controllers = SupplyController.class)
public class SupplyControllerTests {

    @Mock
    private SupplyRepository supplyRepository;



    @Mock
    private ProductRepository productRepository;


    @Test
    public void addition_of_products_to_the_inventory_ReturnCreated() throws Exception {






    }
    }







