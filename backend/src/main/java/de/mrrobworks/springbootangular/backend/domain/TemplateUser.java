package de.mrrobworks.springbootangular.backend.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id") // End Lombok
@Entity
@Table(name = "template_user") // End JPA
public class TemplateUser {

  @Id
  private String id;

  // TODO: Fetch.EAGER replace with @EntityGraph
  @ManyToMany (fetch=FetchType.EAGER)
  @JsonManagedReference
  @JoinTable(name = "template_user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<TemplateRole> roles = new ArrayList<>();
}
