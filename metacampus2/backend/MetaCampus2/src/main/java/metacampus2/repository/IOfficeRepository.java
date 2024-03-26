package metacampus2.repository;

import metacampus2.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOfficeRepository extends JpaRepository<Office, Long> {
    Office findByNumberAndMetaverseName(String officeNumber, String metaverseName);
    List<Office> findAllByMetaverseName(String metaverseName);
}
