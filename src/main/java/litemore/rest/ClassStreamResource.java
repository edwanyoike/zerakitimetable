package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.ClassStream;
import litemore.persistence.ClassStreamRepository;

@ResourceProperties(path = "classStreams")
public interface ClassStreamResource extends PanacheRepositoryResource<ClassStreamRepository, ClassStream, Long> {
}
