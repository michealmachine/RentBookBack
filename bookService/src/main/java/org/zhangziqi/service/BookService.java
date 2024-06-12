package org.zhangziqi.service;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.model.Books;
import org.zhangziqi.rep.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Resource
    BooksRepository booksRepository;
    @Resource
    ModelMapper modelMapper;

    @Transactional
    public String saveBook(BooksDto booksDto) {
        Books books = modelMapper.map(booksDto, Books.class);
        Books savedBook = booksRepository.save(books);
        if (savedBook.getId() != null) {
            return "数据添加成功";
        } else {
            return "数据添加失败，保存的书籍没有ID";
        }
    }

    @Transactional
    public String updateBook(BooksDto booksDto) {
        Integer id = booksDto.getId();
        if (id == null) {
            return "更新失败，书籍的ID不能为空";
        }
        Optional<Books> optionalBooks = booksRepository.findById(id);
        if (optionalBooks.isPresent()) {
            Books books = optionalBooks.get();
            modelMapper.map(booksDto, books);
            booksRepository.save(books);
            return "更新成功";
        } else {
            return "更新失败，没有找到ID为 " + id + " 的书籍";
        }
    }

    @Transactional
    public String deleteBook(Integer id) {
        booksRepository.findById(id).ifPresent(it -> booksRepository.deleteById(it.getId()));
        return "删除成功";
    }

    @Transactional
    public String deleteBookByName(String name) {
        int deletedCount = booksRepository.deleteBooksByBookName(name);
        if (deletedCount > 0) {
            return "删除成功";
        } else {
            return "删除失败，没有找到名为 " + name + " 的书籍";
        }
    }

    public BooksDto queryBooksById(Integer id) {
        return booksRepository.findById(id)
                .map(book -> modelMapper.map(book, BooksDto.class))
                .orElse(null);
    }

    public List<BooksDto> queryBooksByName(String booksName) {
        List<Books> books = booksRepository.findByBookNameLike('%' + booksName + '%');
        return books.stream()
                .map(book -> modelMapper.map(book, BooksDto.class))
                .collect(Collectors.toList());
    }

    public Page<BooksDto> getPage(Pageable page) {
        Page<Books> booksPage = booksRepository.findAll(page);
        List<BooksDto> booksDtos = booksPage.getContent().stream()
                .map(book -> modelMapper.map(book, BooksDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(booksDtos, page, booksPage.getTotalElements());
    }
}
