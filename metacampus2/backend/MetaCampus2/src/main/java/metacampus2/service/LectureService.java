package metacampus2.service;

import metacampus2.model.Lecture;
import metacampus2.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureService implements ILectureService {
    private LectureRepository lectureRepository;


    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public void addNewLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    @Override
    public Lecture getLectureFromMetaverse(String lectureName, LocalDateTime lectureDateTime, String metaverseName) {
        return lectureRepository.findByNameAndDateTimeAndMetaverseName(lectureName, lectureDateTime, metaverseName);
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getAllLecturesFromMetaverse(String metaverseName) {
        return lectureRepository.findAllByMetaverseName(metaverseName);
    }
}


