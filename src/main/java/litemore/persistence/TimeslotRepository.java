package litemore.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.Timeslot;


@ApplicationScoped
public class TimeslotRepository implements PanacheRepository<Timeslot> {
}
