package net.svishch.template.db.entity;

import lombok.Data;
import net.svishch.template.config.security.RoleListApp;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    public String getNameNoPrefix() {
        return  getName().replace(RoleListApp.ROLE_PREFIX,"");
    }
}
