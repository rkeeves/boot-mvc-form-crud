package io.github.rkeeves.bootmvcformcrud.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "todos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;
}
