package metacampus2.repository;

import metacampus2.model.AudioPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAudioPanelRepository extends JpaRepository<AudioPanel, Long> {

    List<AudioPanel> findAllByMetaverseName(String metaverse);
}
