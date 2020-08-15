package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomStatus {

    OPEN("OPEN"),
    CLOSE("CLOSE");

    private final String status;
}
