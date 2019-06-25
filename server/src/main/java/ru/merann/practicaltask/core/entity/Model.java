package ru.merann.practicaltask.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.merann.practicaltask.core.entity.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MODEL_TBL")
@Getter
@Setter
@NoArgsConstructor
public class Model extends AbstractNamedEntity {
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MODEL_OPTION_TBL",
            joinColumns = { @JoinColumn(name = "model_id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id") }
    )
    private Set<Option> options = new HashSet<>();
}
