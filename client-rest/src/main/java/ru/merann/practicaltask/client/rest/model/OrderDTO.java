package ru.merann.practicaltask.client.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderDTO
 */
@Data
@NoArgsConstructor
public class OrderDTO {
  private Long id;
  private NamedDTO brand;
  private NamedDTO model;
  private List<NamedDTO> selectedOptions;
  private String status;
  private String orderDate;
  private NamedDTO user;

  public OrderDTO(Long modelId, String options, Long userId) {
    this.model = new NamedDTO(modelId);
    this.selectedOptions = Arrays.stream(options.split(",")).map(Long::parseLong).map(NamedDTO::new).collect(Collectors.toList());
    this.user = new NamedDTO(userId);
  }
}

