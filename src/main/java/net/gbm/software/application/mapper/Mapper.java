package net.gbm.software.application.mapper;

import lombok.extern.slf4j.Slf4j;
import net.gbm.software.application.data.RequestDemo;
import net.gbm.software.domain.model.Product;

import javax.inject.Singleton;

@Slf4j
public class Mapper {

    public Product mapperModel(RequestDemo request) {
        log.info("mapperModel()");
        return new Product(request);
    }
}
