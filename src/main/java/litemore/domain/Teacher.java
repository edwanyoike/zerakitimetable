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
@Setter
@Getter
@Table(name = "teacher")
public class Teacher implements Serializable {

    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Lesson> lessons = new HashSet<>();


    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.getTeachers().add(this);
    }

    public void setLessons(Set<Lesson> lessons) {
        for (Lesson lesson : lessons
        ) {
            this.addLesson(lesson);

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int userid) {
        this.id = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
