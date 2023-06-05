package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;

import java.util.List;

public interface StudentService extends CrudService<StudentDTO,String> {
    StudentTM searchStudent(String id)throws Exception;
    List<StudentTM> getStudentDataToTable();
}
