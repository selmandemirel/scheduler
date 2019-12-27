package com.sd.scheduler.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class TaskScheduler extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_scheduler")
    @SequenceGenerator(name = "seq_task_scheduler", sequenceName = "seq_task_scheduler")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchedulerType schedulerType;

    @Column
    private String schedulerExpression;


    @Builder
    private TaskScheduler(Task task, SchedulerType schedulerType, String schedulerExpression) {
        this.task = task;
        this.schedulerExpression = schedulerExpression;
        this.schedulerType = schedulerType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskScheduler)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TaskScheduler that = (TaskScheduler) o;
        return id == that.id &&
            task.equals(that.task) &&
            schedulerType.equals(that.schedulerType) &&
            schedulerExpression.equals(that.schedulerExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, task, schedulerType, schedulerExpression);
    }
}
