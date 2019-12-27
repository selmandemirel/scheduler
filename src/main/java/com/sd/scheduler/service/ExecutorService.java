package com.sd.scheduler.service;

import com.sd.scheduler.model.Task;

public interface ExecutorService {

  void execute(Task task);
}
