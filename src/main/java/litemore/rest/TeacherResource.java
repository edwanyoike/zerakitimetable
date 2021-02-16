package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.Teacher;
import litemore.persistence.TeacherRepository;

@ResourceProperties(path = "/teachers")
public interface TeacherResource extends PanacheRepositoryResource<TeacherRepository, Teacher,Long> {
}
