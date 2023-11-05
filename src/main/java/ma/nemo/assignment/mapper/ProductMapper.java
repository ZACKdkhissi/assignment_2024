package ma.nemo.assignment.mapper;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.dto.ProductDto;

public class ProductMapper {

    private static ProductDto ProductDto;

    public static ProductDto map(Product product) {
        ProductDto = new ProductDto();
        ProductDto.setProductId(product.getProductId());
        ProductDto.setProductName(product.getProductName());
        ProductDto.setProductCode(product.getProductCode());

        return ProductDto;

    }
}
