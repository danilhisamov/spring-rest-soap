package ru.merann.practicaltask.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.merann.practicaltask.core.entity.base.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TBL")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractNamedEntity {
    @Column(name = "surname")
    private String surname;
}
