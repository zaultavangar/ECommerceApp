package com.zaultavangar.ecommerce.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPurchaseException extends RuntimeException {
  private final String msg;
}
