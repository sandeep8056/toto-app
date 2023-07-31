package com.task.todo.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.task.todo.dto.UserRequestDto;
import com.task.todo.entities.User;
import com.task.todo.exception.UserNotFoundException;
import com.task.todo.repository.IRoleRepository;
import com.task.todo.repository.IUserRepository;
import com.task.todo.service.IUserService;
import com.task.todo.utils.Mapper;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private UserRoleServiceImpl roleServiceImpl;

	@Transactional
	@Override
	public String saveUser(UserRequestDto userRequest) {

		try {
			List<User> userList = userRepository.findAll();
			// checking already email exisit or not
			userList.stream().forEach(user -> {

				if (user.getEmail().equalsIgnoreCase(userRequest.getEmail())) {
					throw new DataIntegrityViolationException("Email Already exsists");
				}
			});

			User users = userRepository.save(mapper.UserRequestDtoToEntity(userRequest));
			Integer userId = users.getUserId();
			roleServiceImpl.assignRoleToUser(userId, 2);
			return "user saved successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deleteUser(Integer userId) {

		try {

			User user = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("user not found by given id"));

			userRepository.delete(user);
			return "user deleted successfully " + userId;
		} catch (Exception e) {
			throw e;
		}
	}

}
