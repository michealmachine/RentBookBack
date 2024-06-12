package org.zhangziqi.rep;

import org.springframework.data.jpa.repository.Query;
import org.zhangziqi.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {
    @Modifying
    Integer deleteBooksByBookName(String name);
    Page<Books> findAll(Pageable pageable);
    @Modifying
    @Query("update Books b set b.account = b.account - 1 where b.id = :bookId and b.account > 0")
    int decreaseBookQuantity(Integer bookId);
    @Modifying
    @Query("update Books b set b.account = b.account + 1 where b.id = :bookId")
    int increaseBookQuantity(Integer bookId);
    List<Books> findByBookNameLike(String bookName);
}
