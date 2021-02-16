package litemore.domain;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "intake")
public class Intake implements Serializable {

    @Id
    private int id;
    private String graduationYear;

    public Intake(int intakeid, String graduationYear) {
        this.id = intakeid;
        this.graduationYear = graduationYear;
    }
}
