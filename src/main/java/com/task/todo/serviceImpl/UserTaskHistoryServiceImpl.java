package com.task.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo.entities.UserTask;
import com.task.todo.entities.UserTaskHistory;
import com.task.todo.repository.IUserTaskHistoryRepository;
import com.task.todo.service.IUserTaskHistory;

@Service
public class UserTaskHistoryServiceImpl  implements IUserTaskHistory{
	
	@Autowired
	private IUserTaskHistoryRepository userTaskHistoryRepository;

	@Override
	public void maintainHistoryUserTask(UserTask userTask) {
		
		UserTaskHistory userTaskHistory = new UserTaskHistory();
		userTaskHistory.setStatus(userTask.getStatus());
		userTaskHistory.setUpdatedAt(userTask.getLastModifiedDate());
		userTaskHistory.setUserTask(userTask);
		userTaskHistoryRepository.save(userTaskHistory);	
	}

}
