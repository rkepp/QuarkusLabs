/**
 * Grupo Aval Acciones y Valores S.A. CONFIDENTIAL
 *
 * <p>Copyright (c) 2018 . All Rights Reserved.
 *
 * <p>NOTICE: This file is subject to the terms and conditions defined in file 'LICENSE', which is
 * part of this source code package.
 */
package net.gbm.software.application.handler;

import lombok.extern.slf4j.Slf4j;
import net.gbm.software.application.data.RequestDemo;
import net.gbm.software.application.data.ResponseDemo;
import net.gbm.software.domain.model.Product;
import net.gbm.software.domain.service.ProductService;

import javax.inject.Singleton;
import java.util.List;

@Slf4j
@Singleton
public class DemoHandler {
  /*private final Mapper mapper;
  private final ProductPort service;

  public DemoHandler(
      Mapper mapper, ProductPort service) {
    this.mapper = mapper;
    this.service = service;
  }
*/
  public List<ResponseDemo> execute(RequestDemo request) {
    log.info("execute()");
    ProductService p = new ProductService();
    Product product = new Product(request);
    return p.processProduct(product); //service.processProduct(mapper.mapperModel(request));
  }
}
