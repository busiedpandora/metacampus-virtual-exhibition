package metacampus2.service;

import metacampus2.model.Lecturer;
import metacampus2.model.Metaverse;
import metacampus2.model.Office;
import metacampus2.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLecturer implements IServiceLecturer {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer addLecturerByOthers(Lecturer lecturer, Office office, Metaverse metaverse) {

        office.getTeachers().add(lecturer);
        metaverse.getLecturers().add(lecturer);

        lecturer.getMetaverseLecturer().add(metaverse);
        lecturer.setOffice(office);

        return lecturerRepository.save(lecturer);
    }

    @Override
    public Optional<Lecturer> findLecturer(String id) {
        return lecturerRepository.findById(id);
    }

    @Override
    public Optional<Lecturer> updateLecturer(Lecturer lecturer, String id) {

        Optional<Lecturer> checkLecturer = lecturerRepository.findById(id);

        if(checkLecturer.isPresent()){

            if(!lecturer.getFirstName().isEmpty()){
                checkLecturer.get().setFirstName(lecturer.getFirstName());
            }
            if(!lecturer.getLastName().isEmpty()){
                checkLecturer.get().setLastName(lecturer.getLastName());
            }
            if(!lecturer.getCellphone().isEmpty()){
                checkLecturer.get().setCellphone(lecturer.getCellphone());
            }
            if(!lecturer.getOfficePhone().isEmpty()){
                checkLecturer.get().setOfficePhone(lecturer.getOfficePhone());
            }
            lecturerRepository.save(checkLecturer.get());

            return checkLecturer;
        }

        return Optional.empty();
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }
}
