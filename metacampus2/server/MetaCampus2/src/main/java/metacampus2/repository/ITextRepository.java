package metacampus2.repository;

import metacampus2.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITextRepository extends JpaRepository<Text, Long> {
    Text findByTitle(String title);
    Text findByFileName(String fileName);
}
