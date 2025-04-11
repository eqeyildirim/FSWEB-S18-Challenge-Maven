package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "card",schema = "fsweb")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @PrePersist
    @PreUpdate
    private void validateCard() {
        if (type == Type.JOKER) {
            value = null;
            color = null;
        }

        if (value != null && type != null) {
            throw new IllegalStateException("A card cannot have both value and type");
        }
    }

}
