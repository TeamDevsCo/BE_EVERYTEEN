package com.devsco.everyteen.justfortest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {
  private final TestEntityService testEntityService;

  @PostMapping
  public TestEntity save(@RequestBody TestEntity testEntity) {
    return testEntityService.save(testEntity);
  }
}
