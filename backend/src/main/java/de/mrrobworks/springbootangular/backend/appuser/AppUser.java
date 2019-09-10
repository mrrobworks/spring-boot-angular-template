package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "app_user")
public class AppUser {

  @Id private String id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "app_user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<AppRole> roles = new ArrayList<>();
}
