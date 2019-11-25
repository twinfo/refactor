package chapter01_1.Proto04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrototypeTest {

	@Test
	void test() {
		Prototype prototype = new Prototype();
        System.out.println(prototype.statement());
	}

}
