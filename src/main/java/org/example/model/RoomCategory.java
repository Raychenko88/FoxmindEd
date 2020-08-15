package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomCategory {

    SINGLE("SINGLE"),
    DOUBLE("DOUBLE"),
    TRIPLE("TRIPLE");

    private final String category;
}
