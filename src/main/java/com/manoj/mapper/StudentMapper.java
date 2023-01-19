package com.manoj.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.manoj.entity.Student;
import com.manoj.payload.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	Student studentDtoToStudentEntity(StudentDto studentDto);

	StudentDto studentEntityToStudentDto(Student studentEntity);

	List<StudentDto> studentEntityListToStudentDtoList(List<Student> studentEntityList);

}