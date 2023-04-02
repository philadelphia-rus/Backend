package com.philadelphia.api.Database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "units")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Units {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private Long number;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "unit")
    private List<Steps> steps = new ArrayList<>();
    public void addStep(Steps step){
        step.setUnit(this);
        this.steps.add(step);
    }
}
