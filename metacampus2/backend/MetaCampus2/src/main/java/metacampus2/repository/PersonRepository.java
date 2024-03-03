package metacampus2.repository;

import metacampus2.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstNameAndLastNameAndCellphoneAndMetaverseName(String personFirstName, String personLastName,
                                                                  String personCellphone, String metaverseName);
}
