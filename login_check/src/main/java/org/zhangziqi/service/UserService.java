package org.zhangziqi.service;

import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zhangziqi.dto.UserDto;
import org.zhangziqi.model.User;
import org.zhangziqi.rep.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Resource
    UserRepository repository;
    @Resource
    ModelMapper modelMapper;

    public User transDto(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserDto getUserById(Integer id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDto.class);
    }

    public User saveDtoUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return repository.save(user);
    }

    public Boolean checkUser(User askUser) {
        return repository.findUserByUsername(askUser.getUsername())
                .map(target -> target.getPassword().equals(askUser.getPassword())).orElse(false);
    }

    public Integer getId(String username) {
        return repository.findUserByUsername(username).map(User::getId).orElse(null);
    }

    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(userDtos, pageable, users.getTotalElements());
    }


}
