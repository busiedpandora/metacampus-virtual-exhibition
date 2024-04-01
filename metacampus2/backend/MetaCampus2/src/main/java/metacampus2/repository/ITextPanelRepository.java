package metacampus2.repository;

import metacampus2.model.TextPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITextPanelRepository extends JpaRepository<TextPanel, Long> {
    List<TextPanel> findAllByMetaverseName(String metaverseName);
    List<TextPanel> findAllByMetaverseUrlName(String metaverseUrlName);
}
