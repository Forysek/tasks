package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskMapperTestSuite {
    @Test
    public void mapToTaskTest() {
        //Given
        TaskMapper mapper = new TaskMapper();

        TaskDto taskDto = new TaskDto(1L, "Test", "Content");

        //When
        Task mappedTask = mapper.mapToTask(taskDto);

        //Then
        assertTrue(mappedTask.getId().equals(1L));
        assertEquals("Test", mappedTask.getTitle());
        assertEquals("Content", mappedTask.getContent());
    }

    @Test
    public void mapToTaskDtoTest(){
        //Given
        TaskMapper mapper = new TaskMapper();

        Task task = new Task(1L, "Test", "Content");

        //When
        TaskDto mappedToTaskDto = mapper.mapToTaskDto(task);

        //Then
        assertTrue(mappedToTaskDto.getId().equals(1L));
        assertEquals("Test", mappedToTaskDto.getTitle());
        assertEquals("Content", mappedToTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        TaskMapper mapper = new TaskMapper();
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Test", "Content");
        taskList.add(task);

        //When
        List<TaskDto> mappedToTaskDtoList = mapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(mappedToTaskDtoList);
        assertEquals(1, mappedToTaskDtoList.size());
        assertEquals("Test", mappedToTaskDtoList.get(0).getTitle());
    }
}