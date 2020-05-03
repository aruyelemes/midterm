package kz.iitu.midterm.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "username must not be empty")
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    private boolean active;

    @PrePersist
    public void prePersist() {
        if (!this.active) {
            this.active = true;
        }
    }
}
