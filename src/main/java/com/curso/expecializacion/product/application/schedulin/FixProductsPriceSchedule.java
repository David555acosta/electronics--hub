package com.curso.expecializacion.product.application.schedulin;

import com.curso.expecializacion.product.domain.product_repository;
import com.curso.expecializacion.product.infraestructure.database.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {
    private final product_repository productoRepository;

    @Scheduled(fixedRate = 5000)
    public void fixProductsPriceSchedule() {
        log.info("Fixing products price schedule");
        productoRepository.findAll().forEach(producto -> {
            producto.setPrecio(producto.getPrecio() * 1.01);
            productoRepository.save(producto);
        });
        log.info("Fixed products price schedule complete");
    }

}
