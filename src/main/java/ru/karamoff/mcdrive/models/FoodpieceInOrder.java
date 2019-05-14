package ru.karamoff.mcdrive.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_foodpiece")
public class FoodpieceInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @JsonUnwrapped(prefix = "fp")
    @ManyToOne
    @JoinColumn(name = "foodpiece_id", referencedColumnName = "id")
    private Foodpiece foodpiece;

    private Integer amount;
    private Boolean ready;
}
