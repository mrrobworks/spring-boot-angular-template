package de.mrrobworks.springbootangular.backend.approle;

import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "app_role")
public class AppRole {

  @Id private String id;

  private String description;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  private List<AppUser> users;
}
