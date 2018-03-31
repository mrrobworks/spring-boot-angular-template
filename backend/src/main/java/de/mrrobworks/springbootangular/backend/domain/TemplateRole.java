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
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "id") // End Lombok
@Entity
@Table(name = "template_role") // End JPA
@SuppressWarnings("serial")
public class TemplateRole implements GrantedAuthority {

  @Id
  private String id;
  private String description;

  // TODO: Fetch.EAGER replace with @EntityGraph
  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  @JsonBackReference
  private List<TemplateUser> users = new ArrayList<TemplateUser>();

  @Override
  public String getAuthority() {
    return id;
  }
}
