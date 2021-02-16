package litemore.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import litemore.domain.Timeslot;
import litemore.persistence.TimeslotRepository;

@ResourceProperties(path = "timeslots")
public interface TimeslotResource extends PanacheRepositoryResource<TimeslotRepository, Timeslot, Long> {
}
