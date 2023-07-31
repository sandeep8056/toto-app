package com.task.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.todo.dto.PageRequestDto;
import com.task.todo.dto.TaskRequestDto;
import com.task.todo.entities.Task;
import com.task.todo.exception.TaskNotFoundException;
import com.task.todo.repository.ITaskRepository;
import com.task.todo.service.ITaskService;
import com.task.todo.utils.ConstantMessages;
import com.task.todo.utils.ErrorMessageConstant;
import com.task.todo.utils.Mapper;

@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public String createTask(TaskRequestDto taskDto) {

		try {
			Task task = mapper.TaskRequestDtoToTaskEntity(taskDto);
			taskRepository.save(task);
			return "task created successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deleteTask(Integer taskId) {
		try {
			Task task = taskRepository.findById(taskId)
					.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND+" with given id " + taskId));
			taskRepository.delete(task);
			return "task deleted successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String updateTask(Integer taskId, TaskRequestDto taskDto) {

		try {
			Task task = taskRepository.findById(taskId)
					.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND +"with given id "+ taskId));

			task.setTitle(taskDto.getTitle());
			task.setDescription(taskDto.getDescription());
			taskRepository.save(task);
			return ConstantMessages.TASK_UPDATED_SUCCESSFULLY;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Task> getAllTask(PageRequestDto dto) {

		Pageable pageable = new PageRequestDto().getPageable(dto);
		Page<Task> taskPage = taskRepository.findAll(pageable);

		Page<TaskRequestDto> page = taskPage.map(task -> mapper.TaskEntityToTaskRequestDto(task));
		// Page<TaskRequestDto> pageq = new PageImpl<>(taskRequestDto, taskPage,
		return taskPage;
	}

}
