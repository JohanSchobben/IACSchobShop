package iac.schobshop.Schobshop.controller.rest;

import iac.schobshop.Schobshop.command.ProductCommand;
import iac.schobshop.Schobshop.converter.ProductCommandToProduct;
import iac.schobshop.Schobshop.converter.ProductToProductData;
import iac.schobshop.Schobshop.data.ProductData;
import iac.schobshop.Schobshop.data.ProductList;
import iac.schobshop.Schobshop.model.Product;
import iac.schobshop.Schobshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductRestController {

    private ProductService productService;
    private ProductToProductData productToProductData;
    private ProductCommandToProduct productCommandToProduct;

    @GetMapping
    public ProductList getAllProducts(){
        ProductList productList = new ProductList();
        Set<Product> productSet = productService.findAllProducts();
        Set<Resource<ProductData>> productDataSet = new HashSet<>();
        for(Product product : productSet){
            ProductData productData = productToProductData.convert(product);
            Resource<ProductData> productDataResource = new Resource<>(productData);
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getProductById(product.getId()));
            productDataResource.add(linkTo.withRel("prodcuctLink"));
            productDataSet.add(productDataResource);
        }
        productList.setProducts(productDataSet);
        return  productList;
    }
    @GetMapping(path = "/{id}")
    public Resource<ProductData> getProductById(@PathVariable Long id){
        Product product = productService.findProductById(id);
        ProductData productData = productToProductData.convert(product);
        Resource<ProductData> productDataResource = new Resource<>(productData);
        ControllerLinkBuilder linkAll = linkTo(methodOn(this.getClass()).getAllProducts());
        productDataResource.add(linkAll.withRel("allProducts"));
        return productDataResource;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Resource<ProductData> createProduct(@Valid @RequestBody ProductCommand productCommand){
        Product product =  productService.createProduct(productCommandToProduct.convert(productCommand));
        ProductData productData = productToProductData.convert(product);
        Resource<ProductData> productDataResource = new Resource<>(productData);
        ControllerLinkBuilder productLink = linkTo(methodOn(this.getClass()).getProductById(product.getId()));
        ControllerLinkBuilder allProductsLink = linkTo(methodOn(this.getClass()).getAllProducts());
        productDataResource.add(productLink.withRel("productLink"));
        productDataResource.add(allProductsLink.withRel("allProducts"));
        return productDataResource;
    }
    @PutMapping(path = "/{id}")
    public Resource<ProductData> updateProduct(@Valid @RequestBody ProductCommand productCommand, @PathVariable Long id){
        Product product = productService.updateProduct(productCommandToProduct.convert(productCommand),id);
        ProductData productData = productToProductData.convert(product);
        Resource<ProductData> productDataResource = new Resource<>(productData);
        ControllerLinkBuilder allProductLink = linkTo(methodOn(this.getClass()).getAllProducts());
        ControllerLinkBuilder productLink = linkTo(methodOn(this.getClass()).getProductById(product.getId()));
        productDataResource.add(productLink.withRel("productLink"));
        productDataResource.add(allProductLink.withRel("allProducts"));
        return productDataResource;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
