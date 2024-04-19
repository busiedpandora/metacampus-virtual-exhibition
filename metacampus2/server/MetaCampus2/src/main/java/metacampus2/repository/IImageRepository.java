package metacampus2.repository;

import metacampus2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Image findByTitle(String title);
}
