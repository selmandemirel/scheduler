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

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
public class SchedulerType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_scheduler_type")
    @SequenceGenerator(name = "seq_scheduler_type", sequenceName = "seq_scheduler_type")
    long id;

    @Column
    @NotNull
    String name;

    @Builder
    private SchedulerType(String name) {
        this.name = name;
    }
}
