package chapter01_1.Proto01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrototypeTest {

	@Test
	void test() {
		Prototype prototype = new Prototype();
    	prototype.initData();  // 为了测试，暂时放开访问权限
        System.out.println(prototype.statement());
	}

}
