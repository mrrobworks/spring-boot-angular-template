package de.mrrobworks.springbootangular.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id") // End Lombok
@Entity
@Table(name = "app_role") // End JPA
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AppRole implements GrantedAuthority, Serializable {

  @Id
  private String id;
  private String description;

  // TODO: Fetch.EAGER replace with @EntityGraph
  @JsonBackReference(value = "users")
  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  private List<AppUser> users = new ArrayList<>();

  @Override
  public String getAuthority() {
    return id;
  }
}
