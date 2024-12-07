package com.devsco.everyteen.justfortest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "test_entity", schema = "everyteen")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TestEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Builder
  private TestEntity(String name) {
    this.name = name;
  }
}
