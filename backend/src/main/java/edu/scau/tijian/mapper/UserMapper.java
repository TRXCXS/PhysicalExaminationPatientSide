package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE userId=#{userId} AND password=#{password}")
    public User getUserByUserIdAndPassword(User user);

    @Select("SELECT * FROM users WHERE userId=#{userId}")
    public User getUserByUserId(User user);

    @Insert("INSERT users VALUES(#{userId}, #{password}, #{realName}, #{sex}," +
            "#{identityCard}, #{birthday}, #{userType})")
    public Integer saveUser(User user);
}
