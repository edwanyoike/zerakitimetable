package litemore.persistence;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.Lesson;

@ApplicationScoped
public class LessonRepository implements PanacheRepository<Lesson> {
}
