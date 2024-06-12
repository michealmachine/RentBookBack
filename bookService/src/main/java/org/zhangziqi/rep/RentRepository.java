package org.zhangziqi.rep;

import org.springframework.data.jpa.repository.Modifying;
import org.zhangziqi.model.Books;
import org.zhangziqi.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {
    List<Rent> findByUserId(Integer id);
    Rent findByUserIdAndBook(Integer user, Books books);
    @Modifying
    Integer deleteByUserIdAndBook(Integer user,Books books);
}
