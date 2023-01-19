package com.manoj.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.manoj.entity.IssueBook;
import com.manoj.payload.IssueBookDto;

@Mapper(componentModel = "spring")
public interface IssueBookMapper {

	IssueBook issueBookDtoToIssueBookEntity(IssueBookDto issueBookDto);

	IssueBookDto issueBookEntityToIssueBookDto(IssueBook issueBookEntity);

	List<IssueBookDto> issueBookEntityListToIssueBookDtoList(List<IssueBook> issueBookEntityList);

}
