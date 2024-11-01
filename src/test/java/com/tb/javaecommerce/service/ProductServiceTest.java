package com.tb.javaecommerce.service;

import com.tb.javaecommerce.common.ProductStatus;
import com.tb.javaecommerce.domain.Category;
import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.product.ProductRequestDto;
import com.tb.javaecommerce.service.exception.CategoryNotFoundException;
import com.tb.javaecommerce.service.exception.ProductNotFoundException;
import com.tb.javaecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Service Test")
@SpringBootTest(classes = {ProductServiceImpl.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    private static final int CATEGORY_ID = 1;
    private static final String CATEGORY_TITLE = "TEST";
    private static final String CATEGORY_DESCRIPTION = "TEST";
    private static final String PRODUCT_ID = "92cbf62b-abab-451b-9e8f-b092ee27cb62";

    @MockBean
    private CategoryService mockCategoryService;

    @Autowired
    @Spy
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void shouldGetProductList() {
        List<Product> productList = productService.getAllProducts();
        assertNotNull(productList);
    }

    @Test
    @Order(2)
    void shouldGetProductById() {
        Category category = categoryBuilder();

        Product mockProduct = productBuilder(category);

        Mockito.when(productService.getProductById(PRODUCT_ID)).thenReturn(mockProduct);
        assertEquals(mockProduct, productService.getProductById(PRODUCT_ID));
    }

    @Test
    @Order(3)
    void shouldGetNotFoundProduct() {
        Mockito.when(productService.getProductById(PRODUCT_ID))
                .thenThrow(new ProductNotFoundException(PRODUCT_ID));

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(PRODUCT_ID));
    }

    @Test
    @Order(4)
    void shouldCreateProductSuccess() {
        Category category = categoryBuilder();

        ProductRequestDto productRequestDto = new ProductRequestDto(
                "Test",
                "Test",
                150.0,
                CATEGORY_ID,
                ProductStatus.IN_STOCK
        );

        Mockito.when(mockCategoryService.findCategoryById(CATEGORY_ID)).thenReturn(category);
        Product product = productService.createProduct(productRequestDto);

        assertNotNull(product.getId());
        assertEquals("Test", product.getTitle());
        assertEquals("Test", product.getDescription());
        assertEquals(150.0, product.getPrice());
        assertEquals(category, product.getCategory());
        assertEquals(ProductStatus.IN_STOCK, product.getStatus());
    }

    @Test
    @Order(5)
    void shouldThrowExceptionCreateProductFailed() {
        ProductRequestDto productRequestDto = new ProductRequestDto(
                "Test",
                "Test",
                150.0,
                CATEGORY_ID,
                ProductStatus.IN_STOCK
        );

        Mockito.when(productService.createProduct(productRequestDto)).thenThrow(new CategoryNotFoundException(CATEGORY_ID));
        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(productRequestDto));
    }

    @Test
    @Order(6)
    void shouldUpdateProductSuccess() {
        Category category = categoryBuilder();

        ProductRequestDto productRequestDto = new ProductRequestDto(
                "Test",
                "Test",
                150.0,
                CATEGORY_ID,
                ProductStatus.IN_STOCK
        );

        Mockito.when(mockCategoryService.findCategoryById(CATEGORY_ID)).thenReturn(category);
        Product product = productService.updateProduct(productRequestDto, PRODUCT_ID);

        assertNotNull(product.getId());
        assertEquals("Test", product.getTitle());
        assertEquals("Test", product.getDescription());
        assertEquals(150.0, product.getPrice());
        assertEquals(category, product.getCategory());
        assertEquals(ProductStatus.IN_STOCK, product.getStatus());
    }

    @Test
    @Order(7)
    void shouldThrowExceptionUpdateProductFailedProductNotFound() {
        ProductRequestDto productRequestDto = new ProductRequestDto(
                "Test",
                "Test",
                150.0,
                CATEGORY_ID,
                ProductStatus.IN_STOCK
        );

        Mockito.when(productService.updateProduct(productRequestDto, PRODUCT_ID))
                .thenThrow(new ProductNotFoundException(PRODUCT_ID));

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productRequestDto, PRODUCT_ID));
    }

    @Test
    @Order(8)
    void shouldThrowExceptionUpdateProductFailedCategoryNotFound() {
        ProductRequestDto productRequestDto = new ProductRequestDto(
                "Test",
                "Test",
                150.0,
                CATEGORY_ID,
                ProductStatus.IN_STOCK
        );

        Mockito.when(productService.updateProduct(productRequestDto, PRODUCT_ID))
                .thenThrow(new CategoryNotFoundException(CATEGORY_ID));
        assertThrows(CategoryNotFoundException.class, () -> productService.updateProduct(productRequestDto, PRODUCT_ID));
    }

    @Test
    @Order(9)
    void shouldDeleteProductSuccess() {
        Mockito.when(productService.deleteProduct(PRODUCT_ID))
                .thenReturn("Product with ID - " + PRODUCT_ID + " has been deleted");

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(PRODUCT_ID);
    }

    @Test
    @Order(9)
    void shouldThrowExceptionDeleteProductFailedProductNotFound() {
        Mockito.when(productService.deleteProduct("92cbf62b-abab-451b-9e8f-b092ee27cb63"))
                .thenThrow(new ProductNotFoundException("92cbf62b-abab-451b-9e8f-b092ee27cb63"));

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct("92cbf62b-abab-451b-9e8f-b092ee27cb63"));
    }


    private static Category categoryBuilder() {
        return Category.builder()
                .id(CATEGORY_ID)
                .title(CATEGORY_TITLE)
                .description(CATEGORY_DESCRIPTION)
                .build();
    }

    private static Product productBuilder(Category category) {
        return Product.builder()
                .id(UUID.fromString(PRODUCT_ID))
                .title("Test")
                .category(category)
                .status(ProductStatus.IN_STOCK)
                .description("Test")
                .price(150.0)
                .build();
    }
}
