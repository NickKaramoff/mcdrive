package ru.karamoff.mcdrive.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "foodpiece")
    @JsonIgnore
    List<IngredientInFoodpiece> ingredients;
}
