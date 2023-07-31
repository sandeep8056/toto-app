package com.task.todo.dto;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.task.todo.utils.ConstantMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {

	private Integer pageNo = ConstantMessages.PAGE_NUMBER;

	private Integer pageSize = ConstantMessages.PAGE_SIZE;

	private Sort.Direction sort =Direction.ASC;

	private String sortByColumn = "";

	public Pageable getPageable(PageRequestDto dto) {

		Integer page = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
		Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;
		Sort.Direction sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;

		String sortByColumn = Objects.nonNull(dto.getSortByColumn()) ? dto.getSortByColumn() : this.sortByColumn;

		Pageable request;
		if (sortByColumn.equalsIgnoreCase("") ) {
			request = PageRequest.of(page, size, sort);
		} else {
			request = PageRequest.of(page, size, sort, sortByColumn);
		}

		return request;
	}

}
