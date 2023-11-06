package ma.nemo.assignment.web;


import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Threshold;
import ma.nemo.assignment.dto.ReturnDto;
import ma.nemo.assignment.dto.SaleDto;
import ma.nemo.assignment.dto.SupplyDto;

import ma.nemo.assignment.dto.ThresholdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoucheRestTests {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testAddSupplyShouldBeReturn200Statut() {
        String url = "http://localhost:" + port + "/api/supply";

        SupplyDto supplyDto = new SupplyDto();
        supplyDto.setProductCode("ABC111");
        supplyDto.setQuantity(100);

        ResponseEntity<Long> response = restTemplate.postForEntity(url, supplyDto, Long.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddSaleShouldReturn200Statut() {
        String url = "http://localhost:" + port + "/api/sale";

        SaleDto saleDto = new SaleDto();
        saleDto.setProductCode("ABC111");
        saleDto.setUserId(1L);
        saleDto.setQuantity(10);

        ResponseEntity<Long> response = restTemplate.postForEntity(url, saleDto, Long.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testProduct_ReturnShouldReturn200Statut() {
        String url = "http://localhost:" + port + "/api/return";

        ReturnDto returnDto = new ReturnDto();
        returnDto.setProductCode("ABC111");
        returnDto.setQuantity(11);
        returnDto.setReasonForReturn("Produit défectueux");

        ResponseEntity<Long> response = restTemplate.postForEntity(url, returnDto, Long.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetExpiryAlertsShouldRetourn200Statut() {
        String url = "http://localhost:" + port + "/api/expiry-alerts";

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetExpiryAlertsShouldBeNotNull() {
        String url = "http://localhost:" + port + "/api/expiry-alerts";

        ResponseEntity<List<Product>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        List<Product> products = response.getBody();
        Assertions.assertTrue(products != null && !products.isEmpty());
    }

    @Test
    public void testSetThresholdShouldReturn200Statut() {
        String url = "http://localhost:" + port + "/api/set-threshold";

        ThresholdDto thresholdDto = new ThresholdDto();
        thresholdDto.setThresholdQuantity(3000);
        thresholdDto.setProductCode("ABC114");

        ResponseEntity<Long> response = restTemplate.postForEntity(url, thresholdDto, Long.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetProductsBelowThreshold() {
        String url = "http://localhost:" + port + "/api/threshold-alerts";

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        List<Product> products = response.getBody();
        int result =products.size();

        Assertions.assertEquals(1,result);
    }















    }







