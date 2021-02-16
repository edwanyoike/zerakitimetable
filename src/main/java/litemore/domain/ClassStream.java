package litemore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "streams")
public class ClassStream implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @JsonIgnore
    private Intake intake;

    @Transient
    private int intakeId;

    private String name;


    @ManyToMany(mappedBy = "stream", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Lesson> lessons = new HashSet<>();

    public ClassStream(Long streamid, String name) {
        this.id = streamid;
        this.name = name;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.getStream().add(this);
    }

    public void setLessons(Set<Lesson> lessons) {
        for (Lesson lesson : lessons
        ) {
            this.addLesson(lesson);

        }
    }


    public String getClassName() {
        int currentYear = LocalDateTime.now().getYear();
        int graduationYear = Integer.parseInt(intake.getGraduationYear());

        String form = "";

        switch (graduationYear - currentYear) {
            case 0:
                form = "FORMFOUR";
                break;

            case 1:
                form = "FORMTHREE";
                break;
            case 2:
                form = "FORMTWO";
            case 3:
                form = "FORMONE";
                break;

            default:
                form = "ERROR";

        }

        form += getName();

        return form;

    }

    @Override
    public String toString() {
        return "Streams{" +
                "id=" + id +
                ", intake=" + intake +
                ", intakeId=" + intakeId +
                ", name='" + name + '\'' +
                //  ", lessons=" + lessons +
                '}';
    }

    //    public int getIntakeId() {
//        return getIntake().getId();
//    }
}
