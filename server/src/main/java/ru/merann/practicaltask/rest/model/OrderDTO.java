package ru.merann.practicaltask.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.merann.practicaltask.rest.model.NamedDTO;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-06-21T13:42:46.222+03:00[Europe/Moscow]")

public class OrderDTO   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("brand")
  private NamedDTO brand = null;

  @JsonProperty("model")
  private NamedDTO model = null;

  @JsonProperty("selectedOptions")
  @Valid
  private List<NamedDTO> selectedOptions = null;

  @JsonProperty("status")
  private String status;

  @JsonProperty("orderDate")
  private String orderDate;

  @JsonProperty("user")
  private NamedDTO user = null;

  public OrderDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderDTO brand(NamedDTO brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Get brand
   * @return brand
  */
  @ApiModelProperty(value = "")

  @Valid

  public NamedDTO getBrand() {
    return brand;
  }

  public void setBrand(NamedDTO brand) {
    this.brand = brand;
  }

  public OrderDTO model(NamedDTO model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @ApiModelProperty(value = "")

  @Valid

  public NamedDTO getModel() {
    return model;
  }

  public void setModel(NamedDTO model) {
    this.model = model;
  }

  public OrderDTO selectedOptions(List<NamedDTO> selectedOptions) {
    this.selectedOptions = selectedOptions;
    return this;
  }

  public OrderDTO addSelectedOptionsItem(NamedDTO selectedOptionsItem) {
    if (this.selectedOptions == null) {
      this.selectedOptions = new ArrayList<>();
    }
    this.selectedOptions.add(selectedOptionsItem);
    return this;
  }

  /**
   * Get selectedOptions
   * @return selectedOptions
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<NamedDTO> getSelectedOptions() {
    return selectedOptions;
  }

  public void setSelectedOptions(List<NamedDTO> selectedOptions) {
    this.selectedOptions = selectedOptions;
  }

  public OrderDTO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public OrderDTO orderDate(String orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  /**
   * Get orderDate
   * @return orderDate
  */
  @ApiModelProperty(value = "")


  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public OrderDTO user(NamedDTO user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(value = "")

  @Valid

  public NamedDTO getUser() {
    return user;
  }

  public void setUser(NamedDTO user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDTO orderDTO = (OrderDTO) o;
    return Objects.equals(this.id, orderDTO.id) &&
        Objects.equals(this.brand, orderDTO.brand) &&
        Objects.equals(this.model, orderDTO.model) &&
        Objects.equals(this.selectedOptions, orderDTO.selectedOptions) &&
        Objects.equals(this.status, orderDTO.status) &&
        Objects.equals(this.orderDate, orderDTO.orderDate) &&
        Objects.equals(this.user, orderDTO.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brand, model, selectedOptions, status, orderDate, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    selectedOptions: ").append(toIndentedString(selectedOptions)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    orderDate: ").append(toIndentedString(orderDate)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

