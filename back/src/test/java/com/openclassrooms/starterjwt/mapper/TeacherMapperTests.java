package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherMapperTests {

    private TeacherMapper teacherMapper;

    @BeforeEach
    public void setUp() {
        teacherMapper = Mappers.getMapper(TeacherMapper.class);
    }

    @Test
    public void testToEntity() {
        TeacherDto teacherDto = createTeacherDto(1L, "Doe", "John");

        Teacher teacher = teacherMapper.toEntity(teacherDto);

        assertEquals(teacherDto.getId(), teacher.getId());
        assertEquals(teacherDto.getLastName(), teacher.getLastName());
        assertEquals(teacherDto.getFirstName(), teacher.getFirstName());
        assertEquals(teacherDto.getCreatedAt(), teacher.getCreatedAt());
        assertEquals(teacherDto.getUpdatedAt(), teacher.getUpdatedAt());
    }

    @Test
    public void testToDto() {
        Teacher teacher = createTeacher(1L, "Doe", "John");

        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        assertEquals(teacher.getId(), teacherDto.getId());
        assertEquals(teacher.getLastName(), teacherDto.getLastName());
        assertEquals(teacher.getFirstName(), teacherDto.getFirstName());
        assertEquals(teacher.getCreatedAt(), teacherDto.getCreatedAt());
        assertEquals(teacher.getUpdatedAt(), teacherDto.getUpdatedAt());
    }

    @Test
    public void testToEntityList() {
        TeacherDto teacherDto1 = createTeacherDto(1L, "Doe", "John");
        TeacherDto teacherDto2 = createTeacherDto(2L, "Smith", "Jane");

        List<Teacher> teachers = teacherMapper.toEntity(Arrays.asList(teacherDto1, teacherDto2));

        assertEquals(2, teachers.size());
        assertEquals("Doe", teachers.get(0).getLastName());
        assertEquals("Smith", teachers.get(1).getLastName());
    }

    @Test
    public void testToDtoList() {
        Teacher teacher1 = createTeacher(1L, "Doe", "John");
        Teacher teacher2 = createTeacher(2L, "Smith", "Jane");

        List<TeacherDto> teacherDtos = teacherMapper.toDto(Arrays.asList(teacher1, teacher2));

        assertEquals(2, teacherDtos.size());
        assertEquals("Doe", teacherDtos.get(0).getLastName());
        assertEquals("Smith", teacherDtos.get(1).getLastName());
    }

    private Teacher createTeacher(Long id, String lastName, String firstName) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setLastName(lastName);
        teacher.setFirstName(firstName);
        teacher.setCreatedAt(LocalDateTime.now());
        teacher.setUpdatedAt(LocalDateTime.now());
        return teacher;
    }

    private TeacherDto createTeacherDto(Long id, String lastName, String firstName) {
        return new TeacherDto(
                id,
                lastName,
                firstName,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
