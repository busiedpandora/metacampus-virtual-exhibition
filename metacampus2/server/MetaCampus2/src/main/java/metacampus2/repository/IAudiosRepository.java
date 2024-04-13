package metacampus2.repository;

import metacampus2.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAudiosRepository extends JpaRepository<Audio, Long> {
}
