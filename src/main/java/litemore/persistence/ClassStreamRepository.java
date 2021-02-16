package litemore.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.ClassStream;

@ApplicationScoped
public class ClassStreamRepository implements PanacheRepository<ClassStream> {
}
