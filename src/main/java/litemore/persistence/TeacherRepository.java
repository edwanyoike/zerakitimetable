package litemore.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.Teacher;


@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {
}
