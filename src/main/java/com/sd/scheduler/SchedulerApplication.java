package com.sd.scheduler;

import com.sd.scheduler.dao.SchedulerTypeRepository;
import com.sd.scheduler.dao.TaskRepository;
import com.sd.scheduler.dao.TaskSchedulerRepository;
import com.sd.scheduler.dao.TaskTypeRepository;
import com.sd.scheduler.model.SchedulerType;
import com.sd.scheduler.model.Task;
import com.sd.scheduler.model.TaskScheduler;
import com.sd.scheduler.model.TaskType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchedulerApplication.class, args);
  }


  @Bean
  CommandLineRunner loadData(TaskTypeRepository taskTypeRepository, TaskRepository taskRepository, SchedulerTypeRepository schedulerTypeRepository,
      TaskSchedulerRepository taskSchedulerRepository) {
    return (args -> {

      TaskType taskType = taskTypeRepository.save(TaskType.builder().name("Load").build());

      Task task1 = taskRepository.save(Task.builder().name("Loader 1").status("a").taskType(taskType).build());

      Task task2 = taskRepository.save(Task.builder().name("Loader 2").status("a").taskType(taskType).build());

      SchedulerType schedulerType = schedulerTypeRepository.save(SchedulerType.builder().name("cron").build());

      TaskScheduler taskScheduler1 = taskSchedulerRepository
          .save(TaskScheduler.builder().task(task1).schedulerType(schedulerType).schedulerExpression("0 */2 * * * *").build());
      TaskScheduler taskScheduler2 = taskSchedulerRepository
          .save(TaskScheduler.builder().task(task2).schedulerType(schedulerType).schedulerExpression("0 */1 * * * *").build());


    });
  }

}
