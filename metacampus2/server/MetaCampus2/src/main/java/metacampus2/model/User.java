package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String nomeUtente;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users")
    @JsonBackReference
    private List<Metaverse> metaverseList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nome, user.nome) && Objects.equals(cognome, user.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }
}
