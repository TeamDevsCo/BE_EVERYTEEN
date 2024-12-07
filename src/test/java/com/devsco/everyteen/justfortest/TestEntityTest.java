package com.devsco.everyteen.justfortest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestEntityTest {

  @DisplayName("H2 DB 동작 테스트")
  @Test
  void test(@Autowired TestEntityRepository testEntityRepository, @Autowired EntityManager em) {
    //given
    TestEntity test1 = TestEntity.builder().name("홍길동").build();

    //when
    TestEntity saved1 = testEntityRepository.save(test1);

    //then
    Assertions.assertThat(saved1.getName()).isEqualTo("홍길동");
  }
}
