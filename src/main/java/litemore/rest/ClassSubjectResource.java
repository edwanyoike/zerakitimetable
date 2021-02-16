package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.ClassSubject;
import litemore.persistence.ClassSubjectRepository;

@ResourceProperties(path = "/subjects")
public interface ClassSubjectResource extends PanacheRepositoryResource<ClassSubjectRepository, ClassSubject,Long> {
}
