package metacampus2.service;

import metacampus2.model.Lecture;
import metacampus2.repository.ILectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureService implements ILectureService {
    private ILectureRepository ILectureRepository;


    @Autowired
    public LectureService(ILectureRepository ILectureRepository) {
        this.ILectureRepository = ILectureRepository;
    }

    @Override
    public void addNewLecture(Lecture lecture) {
        ILectureRepository.save(lecture);
    }

    @Override
    public Lecture getLectureFromMetaverse(String lectureName, LocalDateTime lectureDateTime, String metaverseName) {
        return ILectureRepository.findByNameAndDateTimeAndMetaverseName(lectureName, lectureDateTime, metaverseName);
    }

    @Override
    public List<Lecture> getAllLectures() {
        return ILectureRepository.findAll();
    }

    @Override
<<<<<<< HEAD
    public List<Lecture> getAllLecturesFromMetaverse(String metaverseName) {
        return ILectureRepository.findAllByMetaverseName(metaverseName);
=======
    public List<Lecture> getAllLecturesFromMetaverseStartingFromCurrentTime(String metaverseName) {
        return lectureRepository.findAllByMetaverseStartingFromCurrentTime(metaverseName);
>>>>>>> 950f17aedde35b96d6d853c1e7187eac93f4e347
    }
}


