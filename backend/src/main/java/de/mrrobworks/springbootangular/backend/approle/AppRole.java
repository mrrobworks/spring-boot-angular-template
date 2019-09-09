package de.mrrobworks.springbootangular.backend.approle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "app_role")
public class AppRole implements GrantedAuthority, Serializable {

  @Id private String id;

  @Size(min = 5, message = "Description must be at least 5 characters long")
  private String description;

  @JsonIgnoreProperties("roles")
  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  private List<AppUser> users = new ArrayList<>();

  @Override
  public String getAuthority() {
    return id;
  }
}
