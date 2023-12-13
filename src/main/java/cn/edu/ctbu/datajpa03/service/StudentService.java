package cn.edu.ctbu.datajpa03.service;

import cn.edu.ctbu.datajpa03.domain.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> findAll();

    /**
     * 只分页的查询
     * @param pageable
     * @return
     */
    Page<Student> findAll(Pageable pageable);

    /**
     * 分页，带过滤的查询
     * @param student
     * @param pageable
     * @return
     */
    public Page<Student> findAll(Example<Student> student,Pageable pageable);


    /**
     * 按id进行查询
     * id 主键，整数
     * **/
     Student findById(Integer id);

     Student getById(Integer id);
    /**
     * 按学号进行查询 Like
     * id 主键，整数
     * **/
     List<Student> findByName(String name);

    /**
     * 按名字和密码查询
     * param name
     * param password
     * return
     */

     List<Student> findByNameAndPassword(String name,String password);

    /**
     * 插入学生信息
     * @param student
     * @return
     */
     Student insert(Student student);

    /**
     * 更新学生信息
     * @param student
     */
     Student  update(Student student);

    /**
     * 删除学生信息
     * @param student
     */
     void delete(Student student);
     void delete(Integer id);

}
