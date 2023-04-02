package com.philadelphia.api.Database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "successed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Successed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "step_id")
    private Steps steps;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Units units;
    @Column(name = "is_completed")
    private Boolean isCompleted = false;
}
