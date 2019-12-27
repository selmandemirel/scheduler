package com.sd.scheduler.service.impl;

import com.sd.scheduler.model.Task;
import com.sd.scheduler.service.ExecutorService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ExecutorServiceImpl implements ExecutorService {

  @Override
  public void execute(Task task) {
    System.out.println("Running Task " + task.getName() + "_" + LocalDateTime.now());
  }
}
