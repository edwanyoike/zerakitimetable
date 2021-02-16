package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.Lesson;
import litemore.persistence.LessonRepository;

@ResourceProperties(path = "lessons")
public interface LessonResource extends PanacheRepositoryResource<LessonRepository, Lesson, Long> {


}
