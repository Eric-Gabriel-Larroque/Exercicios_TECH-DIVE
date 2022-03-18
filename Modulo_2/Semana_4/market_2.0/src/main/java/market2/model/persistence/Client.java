package market2.model.persistence;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 11)
    private String CPF;

    @Column(nullable = false)
    private LocalDate birthDate;

    public Client() {}

    public Client(String name, String CPF, LocalDate birthDate) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CPF='" + CPF + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
