package com.observability.spring_observability;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "test_table")
@Data
public class TestEntity {

    @Id
    private Long id;

}
