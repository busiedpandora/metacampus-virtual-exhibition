package metacampus2.repository;

import metacampus2.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpaceRepository extends JpaRepository<Space, Long> {
    @Query("SELECT s FROM Space s WHERE s.coordinates.x = :x AND s.coordinates.y = :y AND s.coordinates.z = :z " +
            "AND s.metaverse.name = :metaverseName")
    Space findByCoordinatesAndMetaverseName(@Param("x") int x, @Param("y") int y, @Param("z") int z,
                                            @Param("metaverseName") String metaverseName);
    Space findByNameAndMetaverseName(String name, String metaverseName);
}
