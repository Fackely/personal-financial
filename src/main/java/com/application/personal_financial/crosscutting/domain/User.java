package com.application.personal_financial.crosscutting.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String lastName;

    @Transient
    private Double balance;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Movement> movements;

    public Double getBalance() {
        return movements == null || movements.isEmpty() ? 0.0 :
                movements.stream().filter(Movement::getIncome).mapToDouble(Movement::getAmount).sum()
                - movements.stream().filter(Predicate.not(Movement::getIncome)).mapToDouble(Movement::getAmount).sum();
    }
}
