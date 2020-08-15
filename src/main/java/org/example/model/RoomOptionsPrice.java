package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum RoomOptionsPrice {

    BREAKFAST(new BigDecimal(15)),
    CLEANING(new BigDecimal(10));

    private final BigDecimal price;
}
