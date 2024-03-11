package metacampus2.service;

import metacampus2.model.Person;

import java.util.List;

public interface IPersonService {
    void addNewPerson(Person person);

    Person getPersonFromMetaverse(String personFirstName, String personLastName,
                                  String personCellphone, String metaverseName);

    List<Person> getAllPeople();

    List<Person> getAllLecturers();
<<<<<<< HEAD

    List<Person> getAllLecturesFromMetaverse(String metaverseName);
=======
    List<Person> getAllPeopleFromMetaverse(String metaverseName);
>>>>>>> 950f17aedde35b96d6d853c1e7187eac93f4e347
}
