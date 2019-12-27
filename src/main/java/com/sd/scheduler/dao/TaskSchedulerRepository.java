package com.sd.scheduler.dao;

import com.sd.scheduler.model.TaskScheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSchedulerRepository extends JpaRepository<TaskScheduler, Long> {

}
