package com.hr.mapper;

import com.hr.domain.Admin;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    public Admin findUserById(String id);

    public void addAdmin(Admin admin);
}
