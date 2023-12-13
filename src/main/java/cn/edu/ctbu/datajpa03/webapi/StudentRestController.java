package cn.edu.ctbu.datajpa03.webapi;

import cn.edu.ctbu.datajpa03.core.PageUtils;
import cn.edu.ctbu.datajpa03.domain.Student;
import cn.edu.ctbu.datajpa03.service.StudentService;
import cn.edu.ctbu.datajpa03.utils.RUtil;
import cn.edu.ctbu.datajpa03.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webapi/student")
@Slf4j
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    /**
     * 读取的url:/webapi/student/list
     * @return
     */
    @GetMapping("/list")
    public List<Student> getAll(){
        List<Student> students=studentService.findAll();

        return students;
    }

    /**
    @PostMapping("/add")
    public int add(@Valid Student student, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            log.info("新增学生信息出错 {}",bindingResult.getFieldError().getDefaultMessage());

            return -1;
        }

        return 1;
    }

    **/
    @PostMapping("/add")
    public R add(@Valid Student student, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String errorMsg=bindingResult.getFieldError().getDefaultMessage();
            log.info("新增学生信息出错 {}",errorMsg);

            return RUtil.error(-10,errorMsg);
        }


        return RUtil.success();
    }



public R<Page<Student>> list(int pageIndex,int pageSize){
        Pageable pageable=PageRequest.of(pageIndex,pageSize,Sort.by("id").descending());
        return RUtil.success(studentService.findAll(pageable));

    }



    /**
     * 分页读取全部内容
     * @return
     */

    @GetMapping("/getbypage")
    public PageUtils getByPage(@RequestParam(value="page",defaultValue = "0")Integer page,
                               @RequestParam(value="size",defaultValue = "10")Integer size,
                               @RequestParam(value="name",defaultValue = "")String name){


        Sort sort= Sort.by(Sort.Direction.DESC, "id");
        Page<Student> studentPage;

        if(StringUtils.isEmpty(name)){
            Pageable pageable=PageRequest.of(page,size,sort);
            studentPage=studentService.findAll(pageable);
        }else{
            Student student=new Student();
            student.setName((name));

            Pageable pageable= PageRequest.of(0,10,sort);

            ExampleMatcher matcher=ExampleMatcher.matching()
                    .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());
            Example<Student> example=Example.of(student,matcher);

            studentPage=studentService.findAll(example,pageable);
        }

        PageUtils pageUtils=new PageUtils(studentPage.getContent(),studentPage.getTotalElements());
        return pageUtils;


//        Pageable pageable=PageRequest.of(0, 3,sort);
//        Page<Student> studentPage=studentService.findAll(pageable);
//
//        PageUtils pageUtils = new PageUtils(studentPage.getContent(),studentPage.getTotalElements());
//        return pageUtils;
    }




    /**
     * 读取的url:/webapi/student/list
     * @return
     */
    @GetMapping("/get/{id}")
    public Student get(@PathVariable Integer id){
        Student student=studentService.getById(id);
        student.setPassword("");
        return student;
    }



    /**
     * 新增学生
     * @param student
     * @return
     */
    @PostMapping("/insert")
    public Student insert(Student student){

        Student student1= studentService.insert(student);

        return student1;
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PutMapping("/update")
    public  Student update(Student student){
        //读取旧数据

        Student oldstudent= studentService.getById(student.getId());
        if(StringUtils.isEmpty(student.getPassword())){
//            如果没有新密码，那就是旧密码
       student.setPassword(oldstudent.getPassword());
        }

        student=studentService.update(student);

        return student;


    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){

        studentService.delete(id);

    }
}