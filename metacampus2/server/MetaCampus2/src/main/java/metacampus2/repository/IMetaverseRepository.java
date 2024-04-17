package metacampus2.repository;

import metacampus2.model.Metaverse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetaverseRepository extends JpaRepository<Metaverse, Long> {
    Metaverse findByName(String name);
    Metaverse findByUrlName(String urlName);
}
