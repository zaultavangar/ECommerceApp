package com.zaultavangar.ecommerce.product;

import com.zaultavangar.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;
  private final ProductMapper mapper;
  private final EntityManager entityManager;

  public Integer createProduct(ProductRequest request) {
    var product = mapper.toProduct(request);
    return repository.save(product).getId();
  }

  @Transactional
  public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    var productIds = request
        .stream()
        .map(ProductPurchaseRequest::productId)
        .toList();
    var storedProducts = repository.findAllByIdInOrderById(productIds);
    if (productIds.size() != storedProducts.size()){
      throw new ProductPurchaseException("One or more products does not exist");
    }
    var storedRequest = request
        .stream()
        .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
        .toList();

    var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
    for (int i = 0; i < storedProducts.size(); i++){
      var product = storedProducts.get(i);
      var productRequest = storedRequest.get(i);

      Double currentQuantity = product.getAvailableQuantity();
      if (currentQuantity < productRequest.quantity()) {
        throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
      }
      var newAvailableQuantity = currentQuantity - productRequest.quantity();
      product.setAvailableQuantity(newAvailableQuantity);
      repository.save(product);
      purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
    }

    entityManager.flush();

    return purchasedProducts;
  }

  public ProductResponse findById(Integer productId) {
    return repository.findById(productId)
        .map(mapper::toProductResponse)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
  }

  public List<ProductResponse> findAll() {
    return repository.findAll()
        .stream()
        .map(mapper::toProductResponse)
        .collect(Collectors.toList());
  }
}
