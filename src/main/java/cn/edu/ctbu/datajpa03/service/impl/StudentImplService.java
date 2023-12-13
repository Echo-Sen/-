package cn.edu.ctbu.datajpa03.service.impl;

import cn.edu.ctbu.datajpa03.domain.Student;
import cn.edu.ctbu.datajpa03.repository.StudentRepository;
import cn.edu.ctbu.datajpa03.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImplService implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();

    }

    /**
     * 只分页的查询
     * @param pageable
     * @return
     */
    public Page<Student> findAll(Pageable pageable){
        return studentRepository.findAll(pageable);
    }

public Page<Student> findAll(Example<Student> student,Pageable pageable){
        return studentRepository.findAll(student,pageable);
};


    /**
     * 按id进行查询
     * id 主键，整数
     * **/
    public Student findById(Integer id){

        return studentRepository.findById(id).orElse(null);
    }


    public Student getById(Integer id){
        Student student=studentRepository.findById(id).orElse(null);
        return student;
    }
    /**
     * 按学号进行查询 Like
     * id 主键，整数
     * **/
    public List<Student> findByName(String name){

        return studentRepository.findByNameLike(name);
    }

    /**
     * 按名字和密码查询
     * param name
     * param password
     * return
     */

    public List<Student> findByNameAndPassword(String name,String password){

        return studentRepository.findByNameAndPassword(name,password);
    }

    /**
     * 插入学生信息
     * @param student
     * @return
     */
    public Student insert(Student student){
        studentRepository.save(student);
        return student;
    }

    /**
     * 更新学生信息
     * @param student
     */
    public Student  update(Student student){
       return studentRepository.save(student);
    }

    /**
     * 删除学生信息
     * @param student
     */
    public void delete(Student student){
        studentRepository.save(student);

    }
    public void delete(Integer id){
        Student student=new Student();
        student.setId(id);
        studentRepository.delete(student);
    }

}
