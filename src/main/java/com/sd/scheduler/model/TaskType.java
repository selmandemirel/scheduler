package com.sd.scheduler.model;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class TaskType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_type")
    @SequenceGenerator(name = "seq_task_type", sequenceName = "seq_task_type")
    private long id;

    @Column
    @NotNull
    private String name;


    @Builder
    TaskType(String name) {
        this.name = name;
    }
}
