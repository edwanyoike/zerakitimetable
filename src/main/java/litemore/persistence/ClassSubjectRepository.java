package litemore.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import litemore.domain.ClassSubject;

@ApplicationScoped
public class ClassSubjectRepository implements PanacheRepository<ClassSubject> {
}
