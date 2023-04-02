package com.philadelphia.api.Database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "steps")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Steps {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Units unit;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypesSteps type;
    @Column(name = "href_to_md")
    private String mdFile;
    @Column(name = "href_to_video")
    private String video;
    @Column(name = "question_file")
    private String question;
    @Column(name = "number")
    private Long number;
    @Column(name = "name")
    private String name;
}
