package cn.itcast.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@Controller
@RequestMapping("new/user")
public class NewUserController {
    @Autowired
    private UserService userService;
    //查找
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<User> queryUserById(@PathVariable("id") Long id){
        User user = userService.queryUserById(id);
        try{
            if(null == user){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    //新建
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addNewUser(User user){
        boolean bool = userService.addNewUser(user);
        try {
            if(bool)
                return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    //更新
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user){
        Integer flag = userService.updateUser(user);
        try {
            if(flag == 1)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  //删除
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserById(@RequestParam(value = "id",defaultValue = "0") Long id){
        try {
            if(id.intValue() == 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            Integer flag = userService.deleteUserById(id);
            if(flag == 1)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
