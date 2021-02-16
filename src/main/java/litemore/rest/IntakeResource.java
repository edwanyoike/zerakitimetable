package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.Intake;
import litemore.persistence.IntakeRepository;

@ResourceProperties(path = "/intakes")
public interface IntakeResource extends PanacheRepositoryResource<IntakeRepository, Intake,Long> {
}
