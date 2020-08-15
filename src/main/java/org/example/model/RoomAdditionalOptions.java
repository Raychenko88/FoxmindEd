package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomAdditionalOptions {

    BREAKFAST("BREAKFAST"),
    CLEANING("CLEANING");

    private final String name;

}
