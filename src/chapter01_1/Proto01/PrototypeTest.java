package chapter01_1.Proto01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrototypeTest {

	@Test
	void test() {
		Prototype prototype = new Prototype();
    	prototype.initData();  // Ϊ�˲��ԣ���ʱ�ſ�����Ȩ��
        System.out.println(prototype.statement());
	}

}
