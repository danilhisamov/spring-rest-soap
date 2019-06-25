package ru.merann.practicaltask.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.merann.practicaltask.core.entity.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OPTION_TBL")
@Getter
@Setter
@NoArgsConstructor
public class Option extends AbstractNamedEntity {
    @ManyToMany(mappedBy = "options")
    private Set<Model> models = new HashSet<>();
}
