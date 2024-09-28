package com.kyw.spring.controller;

import com.kyw.spring.aspect.HttpAspect;
import com.kyw.spring.domain.Result;
import com.kyw.spring.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

//    @Autowired
//    private GirlRepository girlRepository;
//
//    @Autowired
//    private GirlService girlService;
//    /**
//     * 查询所有列表
//     * @return
//     */
//    @GetMapping(value = "/girls")
//    public List<Girl> girlList(){
//        //System.out.println("girlList我在中间啊啊啊啊");
//        logger.info("girlList我在中间啊啊啊啊");
//        return girlRepository.findAll();
//    }
//
//    /**
//     * 添加一个Girl信息
//     * @return
//     */
//    @PostMapping(value = "/girls")
//    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            Result result = new Result();
//            result.setCode(1);
//            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
//            return result;
//        }
//        girl.setAge(girl.getAge());
//        girl.setCupSize(girl.getCupSize());
//
//        Result result = new Result();
//        result.setCode(0);
//        result.setMsg("成功");
//        result.setData(girl);
//        return result;
//    }
//
//    /**
//     * 查询一个Girl的信息
//     * @param id
//     * @return
//     */
//    @GetMapping(value = "/girls/{id}")
//    public Girl girlFindOne(@PathVariable("id")Integer id){
//        return girlRepository.findOne(id);
//    }
//
//    /**
//     * 修改一个girl的信息
//     * @param id
//     * @param cupSize
//     * @param age
//     * @return
//     */
//    @PutMapping(value = "/girls/{id}")
//    public Girl girlUpdate(@PathVariable("id")Integer id,
//                             @RequestParam("cupSize")String cupSize,
//                             @RequestParam("age")Integer age){
//        Girl girl = new Girl();
//        girl.setId(id);
//        girl.setCupSize(cupSize);
//        girl.setAge(age);
//
//        return girlRepository.save(girl);
//    }
//
//    //删除
//    @DeleteMapping(value = "/girls/{id}")
//    public void girlDelete(@PathVariable("id")Integer id){
//        girlRepository.delete(id);
//    }
//
//    //通过年龄来查询
//    @GetMapping(value = "/girls/age/{age}")
//    public List<Girl> girlByAge(@PathVariable("age")Integer age){
//        return girlRepository.findByAge(age);
//    }
//
//    //事务管理
//    @PostMapping("/girls/two")
//    public void girlTwo(){
//        girlService.insertTwo();
//    }
}
