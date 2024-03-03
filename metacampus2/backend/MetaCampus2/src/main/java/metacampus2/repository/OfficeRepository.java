package metacampus2.repository;


import metacampus2.model.Classroom;
import metacampus2.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office findByNumberAndMetaverseName(String officeNumber, String metaverseName);
}
