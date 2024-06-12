package org.zhangziqi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "rent")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    Integer userId;
    Date borrowDate;
    Date dueDate;
    @ToString.Exclude
    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Books book;

    @PrePersist
    public void prePersist() {
        this.borrowDate = new Date(); // 设置当前时间为借阅时间
    }
}

