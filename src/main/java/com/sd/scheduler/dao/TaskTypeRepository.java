package com.sd.scheduler.dao;

import com.sd.scheduler.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}
