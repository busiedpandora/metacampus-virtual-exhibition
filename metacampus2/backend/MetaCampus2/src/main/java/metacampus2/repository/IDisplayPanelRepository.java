package metacampus2.repository;

import metacampus2.model.DisplayPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDisplayPanelRepository extends JpaRepository<DisplayPanel, Long> {
    List<DisplayPanel> findAllByMetaverseName(String metaverseName);
    List<DisplayPanel> findAllByMetaverseUrlName(String metaverseUrlName);
}
