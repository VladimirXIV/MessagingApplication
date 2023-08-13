package just.education.security_messaging_app.model;

import java.util.Set;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Document(collection = "roles")
public class Role {

    @Id
    private Long id;
    private String name; // Ex : ADMIN, USER

    @DBRef
    private Set<Permission> permissions;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {

        return this.getPermissions()
                .stream()
                .map(Permission::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id)
                && Objects.equals(name, role.name)
                && Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, permissions);
    }
}