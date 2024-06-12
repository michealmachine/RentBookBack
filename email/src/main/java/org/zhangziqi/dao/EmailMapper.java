package org.zhangziqi.dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.zhangziqi.dto.EmailDetails;

@Mapper
public interface EmailMapper {
    @Insert("INSERT INTO email (date, email, bookName) VALUES (#{date}, #{email}, #{bookName})")
    void insert(EmailDetails emailDetails);
}
