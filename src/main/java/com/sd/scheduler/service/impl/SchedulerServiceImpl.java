package com.sd.scheduler.service.impl;

import com.sd.scheduler.dao.TaskSchedulerRepository;
import com.sd.scheduler.model.TaskScheduler;
import com.sd.scheduler.service.ExecutorService;
import com.sd.scheduler.service.SchedulerService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

  @Autowired
  ExecutorService executorService;

  @Autowired
  TaskSchedulerRepository taskSchedulerRepository;

  @Autowired
  private Environment environment;

  private ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();


  private Map<TaskScheduler, Future> taskSchedulerMap = new ConcurrentHashMap<>();

  @PostConstruct
  private void init() {
    threadPoolTaskScheduler.initialize();
  }

  //Reload Task Schedules every minute
  @Scheduled(fixedRate = 60_000)
  @Override
  public void load() {
    log.info("Loading Task Schedulers");

    List<TaskScheduler> taskSchedulers = taskSchedulerRepository.findAll();

    //remove
    List<TaskScheduler> removedSchedulers =
        taskSchedulerMap
            .entrySet()
            .stream()
            .map(Map.Entry::getKey)
            .filter(t -> !taskSchedulers.contains(t))
            .collect(Collectors.toList());

    removedSchedulers.stream()
        .forEach(this::remove);

    //add
    taskSchedulers.stream()
        .filter(t -> !taskSchedulerMap.containsKey(t))
        .forEach(this::add);

  }

  @Override
  public void add(TaskScheduler scheduler) {
    log.info("Adding Scheduler : {}", scheduler.toString());

    //double check if already exist
    if (taskSchedulerMap.containsKey(scheduler)) {
      this.remove(scheduler);
    }

    Trigger trigger = new CronTrigger(scheduler.getSchedulerExpression());

    PeriodicTrigger periodicTrigger = new PeriodicTrigger(1);

    Runnable runnable = () -> {
      executorService.execute(scheduler.getTask());
    };

    try {

      ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnable, trigger);

      taskSchedulerMap.put(scheduler, future);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void remove(TaskScheduler scheduler) {
    log.info("Removing Scheduler {}", scheduler.toString());

    Future<?> future = taskSchedulerMap.get(scheduler);

    try {
      taskSchedulerMap.remove(scheduler);

      if (future instanceof ScheduledFuture) {
        future.cancel(false);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
