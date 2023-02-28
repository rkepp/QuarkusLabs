package net.gbm.software.domain.port.api;

import net.gbm.software.application.data.ResponseDemo;
import net.gbm.software.domain.model.Product;

import java.util.List;

public interface ProductPort {
    List<ResponseDemo> processProduct(Product product);
}
