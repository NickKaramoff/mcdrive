package ru.karamoff.mcdrive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float sum;
    private LocalDateTime time;
    private Boolean ready;
    private Boolean archived;

    @ManyToMany
    @JoinTable(name = "order_foodpiece",
               joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "foodpiece_id", referencedColumnName = "id"))
    private List<Foodpiece> foodpieces;
}
