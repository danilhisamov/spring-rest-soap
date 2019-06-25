package ru.merann.practicaltask.client.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NamedDTO
 */
@Data
@NoArgsConstructor
public class NamedDTO {
  private Long id;
  private String name;

  public NamedDTO(Long id) {
    this.id = id;
  }
}

