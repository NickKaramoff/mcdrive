package ru.karamoff.mcdrive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodpiece")
public class Foodpiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float cost;
    private Boolean available;
    private Boolean ready; // null if not in order

    @ElementCollection
    @MapKeyColumn(name = "ingredient_id")
    @Column(name = "amount")
    @CollectionTable(name = "foodpiece_ingredient", joinColumns = @JoinColumn(name = "foodpiece_id"))
    private Map<Ingredient, Integer> ingredients;
}
