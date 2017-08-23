package com.lshaci.springboot.dubbo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboConsumerApplication.class)
public abstract class AbsSpringBaseTest<B> {

	@Autowired
	protected B bean;
}
