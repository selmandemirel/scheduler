package com.sd.scheduler.service;

import com.sd.scheduler.model.TaskScheduler;

public interface SchedulerService {

  void load();

  void add(TaskScheduler scheduler);

  void remove(TaskScheduler taskScheduler);

}
