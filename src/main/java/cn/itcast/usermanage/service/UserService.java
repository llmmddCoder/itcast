package cn.itcast.usermanage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.usermanage.bean.EasyUIGrid;
import cn.itcast.usermanage.mapper.UserMapper;
import cn.itcast.usermanage.pojo.User;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public EasyUIGrid queryUserList(Integer page , Integer rows) {
        //设置分页参数
        PageHelper.startPage(page, rows);
        Example example = new Example(User.class) ;
        //设置排序条件，倒排序
        example.setOrderByClause("created DESC");
        //查询user数据
        List<User> users= this.userMapper.selectByExample(example);//record 查询条件
        
        //获取分页后的信息
        PageInfo<User> info = new PageInfo<User>(users);
        
        return new EasyUIGrid(info.getTotal(),info.getList());
    }

    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public boolean addNewUser(User user) {
        return this.userMapper.insert(user) == 1;
    }

    public Integer updateUser(User user) {
        user.setUpdated(new Date());
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    public Integer deleteUserById(Long id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }
    
}
