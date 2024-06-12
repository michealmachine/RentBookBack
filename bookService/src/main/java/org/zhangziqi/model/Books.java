package org.zhangziqi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})

@Entity
@Data
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String bookName;
    String disc;
    @NotNull
    Integer account;
    @ToString.Exclude
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Rent> rents;

    public Books(Integer id) {
        this.id = id;
    }
}
