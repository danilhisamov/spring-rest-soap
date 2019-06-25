package ru.merann.practicaltask.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.merann.practicaltask.core.entity.base.AbstractEntity;
import ru.merann.practicaltask.core.entity.base.AbstractNamedEntity;
import ru.merann.practicaltask.core.entity.enums.StatusEnum;
import ru.merann.practicaltask.rest.model.OrderDTO;
import ru.merann.practicaltask.soap.jaxb.State;

import javax.persistence.*;
import javax.xml.bind.JAXBElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDER_TBL")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private StatusEnum status;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @ManyToMany
    @JoinTable(
            name = "ORDER_OPTION_TBL",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id") }
    )
    private Set<Option> selectedOptions = new HashSet<>();

    @Transient
    public OrderDTO toRestDTO() {
        OrderDTO dto = new OrderDTO();
        dto.setId(id);
        dto.brand(model.getBrand().toRestDTO());
        dto.setModel(model.toRestDTO());
        dto.setSelectedOptions(selectedOptions.stream().map(AbstractNamedEntity::toRestDTO).collect(Collectors.toList()));
        dto.setStatus(status.toString());
        dto.setOrderDate(orderDate.toString());
        dto.setUser(user.toRestDTO());

        return dto;
    }

    @Transient
    public ru.merann.practicaltask.soap.jaxb.Order toSoapDTO() {
        ru.merann.practicaltask.soap.jaxb.Order dto = new ru.merann.practicaltask.soap.jaxb.Order();
        dto.setId(id);
        dto.setBrand(model.getBrand().toSoapDTO());
        dto.setModel(model.toSoapDTO());
        dto.getSelectedOptions().addAll(selectedOptions.stream().map(AbstractNamedEntity::toSoapDTO).collect(Collectors.toList()));
        dto.setState(State.fromValue(getStatus().name()));
        dto.setOrderDate(orderDate.toString());
        dto.setUser(user.toSoapDTO());

        return dto;
    }
}
