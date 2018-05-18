package de.mrrobworks.springbootangular.backend.domain;

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
@SuppressWarnings("serial")
public class AppRole implements GrantedAuthority {

  @Id
  private String id;
  private String description;

  // TODO: Fetch.EAGER replace with @EntityGraph
  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  @JsonBackReference
  private List<AppUser> users = new ArrayList<>();

  @Override
  public String getAuthority() {
    return id;
  }
}
