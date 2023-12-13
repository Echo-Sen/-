package cn.edu.ctbu.datajpa03.service;


import cn.edu.ctbu.datajpa03.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class StudentServiceTest {
    @Autowired
    private  StudentService studentService;

    @Test
    void findAll(){
        List<Student> teachers= studentService.findAll();
        assertNotNull(teachers);
    }

    /**
     * 通过id查询
     */
    @Test
    void findById(){
        Student student = studentService.findById(4);
        assertNotNull(student);
    }

    /**
     * 查询名字中含有李的人
     */
    @Test
    void findAByNameLike(){
        List<Student> students= studentService.findByName("李%");
        assertNotNull(students);
    }

    /**
     * 通过姓名和密码查询
     */
    @Test
    void findByNameAndPassword(){
        List<Student> students= studentService.findByNameAndPassword("李贤枻","463579");
        assertNotNull(students);
    }
    @Test
    void findByLateDate(){
        List<Student> students= studentService.findAll();
        String maxYear = students.get(0).getNo().substring(0, 4); // 截取前四位数字作为年份
        Student studentMaxYear= students.get(0);
        for(Student student:students){
            String year=student.getNo().substring(0,4);
            if(year.compareTo(maxYear)>0){
                maxYear=year;
               studentMaxYear=student;
            }
        }
        assertNotNull(students);

    }

    /**
     * 插入信息
     */
    @Test
    void insert(){

        Student student=new Student();
        student.setName("张三1");
        student.setPassword("666666");
        student.setSex(2);
        student.setNo("19980102");
        student.setAge(57);

        studentService.insert(student);
        assertNotNull(student.getId());
    }

    /**
     * 修改信息，通过id查找要修改的人的信息
     */
    @Test
    void update(){

        Student student=studentService.findById(2);
        student.setName("赵六");

        studentService.update(student);
        assertEquals(student.getName(),"赵六");
    }

    /**
     * 删除信息，通过id删除
     */
    @Test
    void delete(){
        studentService.delete(9);
        Student student=studentService.findById(9);
        assertNull(student);
    }
@Test
    public void filterByName(){

        Student student=new Student();
        student.setName("李贤枻");

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable= PageRequest.of(0,10,sort);

        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Student> example=Example.of(student,matcher);

    // Example<Student> example=Example.of(student);
        Page<Student> studentPage=studentService.findAll(example,pageable);

        System.out.println(studentPage);
    }




}