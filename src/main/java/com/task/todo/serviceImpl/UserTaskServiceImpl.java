package com.task.todo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.todo.dto.IUserTaskResponseDto;
import com.task.todo.dto.UserTaskAssignDto;
import com.task.todo.entities.Task;
import com.task.todo.entities.User;
import com.task.todo.entities.UserTask;
import com.task.todo.entities.UserTaskId;
import com.task.todo.enums.Status;
import com.task.todo.exception.TaskNotFoundException;
import com.task.todo.exception.UserNotFoundException;
import com.task.todo.exception.UserTaskNotFoundException;
import com.task.todo.repository.ITaskRepository;
import com.task.todo.repository.IUserRepository;
import com.task.todo.repository.IUserTaskRepository;
import com.task.todo.service.IUserTaskService;
import com.task.todo.utils.ConstantMessages;
import com.task.todo.utils.ErrorMessageConstant;

@Service
public class UserTaskServiceImpl implements IUserTaskService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserTaskHistoryServiceImpl userTaskHistoryServiceImpl;

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private IUserTaskRepository userTaskRepository;

	@Transactional
	@Override
	public String assignTaskToUser(UserTaskAssignDto userTaskDto) {

		User user = userRepository.findById(userTaskDto.getUserId()).orElse(null);

		if (user == null) {
			throw new UserNotFoundException("user not found exception");
		}

		// reterieving all task as per given id
		List<Task> tasks = taskRepository.findAllById(userTaskDto.getTaskId());

		// getting all id of given task
		List<Integer> foundTaskIds = tasks.stream().map(task -> task.getTaskId()).collect(Collectors.toList());

		// checking and collecting all task id that are availiable in db
		List<Integer> taskIds = userTaskDto.getTaskId();

		List<Integer> notFoundTaskIds = taskIds.stream().filter(id -> !foundTaskIds.contains(id))
				.collect(Collectors.toList());

		if (!notFoundTaskIds.isEmpty()) {
			throw new TaskNotFoundException("Task Not found for Ids " + notFoundTaskIds);
		}

		List<UserTask> userTask = new ArrayList<>();

		for (Task task : tasks) {
			Integer taskId = task.getTaskId();
			Integer userId = user.getUserId();

			UserTaskId userTaskId = new UserTaskId(userId, taskId);

			UserTask newUserTasks = new UserTask();

			newUserTasks.setUserTaskId(userTaskId);
			newUserTasks.setStatus(Status.TO_DO);
			newUserTasks.setUser(user);
			newUserTasks.setTask(task);

			userTask.add(newUserTasks);
		}

		userTaskRepository.saveAll(userTask);

		userTask.forEach(newuserTask -> {
			userTaskHistoryServiceImpl.maintainHistoryUserTask(newuserTask);
		});

		return "Tasks assigned  to user successfully";
	}

	@Transactional
	@Override
	public String updateStatus(Integer userId, Integer taskId) {

		try {

			User user = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException(ErrorMessageConstant.USER_NOT_FOUND));

			Optional<UserTask> optTask = user.getUserTask().stream()
					.filter(gettask -> gettask.getTask().getTaskId().equals(taskId)).findAny();

			if (optTask.isEmpty()) {
				throw new UserTaskNotFoundException("user with task not found ");
			}

			System.out.println(optTask.get().getUserTaskId().getTaskId());

			UserTask userTask = optTask.get();

			if (userTask.getStatus().equals(Status.TO_DO)) {
				userTask.setStatus(Status.IN_PROGRESS);
			} else if (userTask.getStatus().equals(Status.IN_PROGRESS)) {
				userTask.setStatus(Status.COMPLETED);
			}

			userTaskRepository.save(userTask);
			userTaskHistoryServiceImpl.maintainHistoryUserTask(userTask);

			return "Status update Successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<IUserTaskResponseDto> getUserTaskByUserId(Integer userId, Integer page, Integer size) {
		try {

			Pageable pageable = PageRequest.of(page, size);

			userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

			Page<IUserTaskResponseDto> userTasks = userTaskRepository.getTaskByUserId(userId, pageable);
			return userTasks;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<IUserTaskResponseDto> getTaskAsPerFilter(Integer userId, String status, Date startDate, Date lastDate) {
		
	
		return null;
	}

}