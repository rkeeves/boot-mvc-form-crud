package io.github.rkeeves.bootmvcformcrud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
