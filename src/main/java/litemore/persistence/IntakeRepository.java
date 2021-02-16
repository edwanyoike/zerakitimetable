package litemore.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.Intake;

@ApplicationScoped
public class IntakeRepository implements PanacheRepository<Intake> {
}
