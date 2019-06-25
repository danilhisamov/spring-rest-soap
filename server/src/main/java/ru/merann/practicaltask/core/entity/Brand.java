package ru.merann.practicaltask.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.merann.practicaltask.core.entity.base.AbstractNamedEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BRAND_TBL")
@Getter
@Setter
@NoArgsConstructor
public class Brand extends AbstractNamedEntity {
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Model> models = new HashSet<>();
}
