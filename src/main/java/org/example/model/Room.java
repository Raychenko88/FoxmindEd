package org.example.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String category;
    private BigDecimal price;
    @Column(name = "additional_options", length = 200)
    private Set<String> additionalOptions;
    @Column(name = "start_date", length = 200)
    private LocalDate startDate;
    @Column(name = "end_date", length = 200)
    private LocalDate endDate;
    private String status;
}
