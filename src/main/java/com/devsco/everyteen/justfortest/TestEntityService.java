package com.devsco.everyteen.justfortest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TestEntityService {
  private final TestEntityRepository testEntityRepository;

  public TestEntity save(TestEntity testEntity) {
    return testEntityRepository.save(testEntity);
  }
}
