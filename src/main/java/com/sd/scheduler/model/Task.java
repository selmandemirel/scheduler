package com.sd.scheduler.model;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task")
    @SequenceGenerator(name = "seq_task", sequenceName = "seq_task")
    int Id;

    @Column
    @NotNull
    String name;

    @Column
    String status;

    @ManyToOne
    TaskType taskType;

    @Builder
    private Task(String name, String status, TaskType taskType) {
        this.name = name;
        this.status = status;
        this.taskType = taskType;
    }
}
