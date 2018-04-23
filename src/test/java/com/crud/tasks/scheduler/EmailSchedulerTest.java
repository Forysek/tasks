package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Captor
    private ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    @Ignore
    public void sizeEqualsOne() throws Exception {
        //Given
        when(taskRepository.count()).thenReturn(1L);
        String adminMail = "adminMailMock";
        when(adminConfig.getAdminMail()).thenReturn(adminMail);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail mailCaptored = mailArgumentCaptor.getValue();
        assertEquals(mailCaptored.getMessage(), "Currently in database you got: 1 task");
        assertTrue(mailCaptored.getMailCc().isEmpty());
        assertEquals(mailCaptored.getMailTo(), adminMail);
        assertEquals(mailCaptored.getSubject(), "Tasks: Once a day email");
    }

    @Test
    @Ignore
    public void sizeEqualsMoreThanOne() throws Exception {
        //Given
        when(taskRepository.count()).thenReturn(2L);
        String adminMail = "adminMailMock";
        when(adminConfig.getAdminMail()).thenReturn(adminMail);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail mailCaptored = mailArgumentCaptor.getValue();
        assertEquals(mailCaptored.getMessage(), "Currently in database you got: 2 tasks");
        assertTrue(mailCaptored.getMailCc().isEmpty());
        assertEquals(mailCaptored.getMailTo(), adminMail);
        assertEquals(mailCaptored.getSubject(), "Tasks: Once a day email");
    }

}