package ru.merann.practicaltask.core.entity.base;

import lombok.Getter;
import lombok.Setter;
import ru.merann.practicaltask.rest.model.NamedDTO;
import ru.merann.practicaltask.soap.jaxb.Named;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractNamedEntity extends AbstractEntity {
    @Column(name = "name")
    protected String name;

    @Transient
    public NamedDTO toRestDTO() {
        NamedDTO namedDTO = new NamedDTO();
        namedDTO.setId(id);
        namedDTO.setName(name);

        return namedDTO;
    }

    @Transient
    public Named toSoapDTO() {
        Named namedDTO = new Named();
        namedDTO.setId(id);
        namedDTO.setName(name);

        return namedDTO;
    }
}
