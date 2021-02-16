package litemore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "subject")
public class ClassSubject implements Serializable {
    @Id
    private Long id;
    private String name;
    private int code;


    @ManyToMany(mappedBy = "classSubjects", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Lesson> lessons = new HashSet<>();

    public ClassSubject(Long id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.getClassSubjects().add(this);
    }

    public void setLessons(Set<Lesson> lessons) {
        for (Lesson lesson : lessons
        ) {
            this.addLesson(lesson);

        }
    }

}
