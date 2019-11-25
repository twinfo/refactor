package chapter01_1.Proto02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prototype {
	private DataProcessBO dataBO;
	public static void main(String[] args) {
		Prototype prototype = new Prototype();
		System.out.println(prototype.statement());
	}

	

	//׼�����ݣ����ݸ�renderPlainText
	public String statement() {
		dataBO = new DataProcessBO();
		return renderPlainText(dataBO.createResultData());
	}

	// �滻ԭ�е��õ�
	private String renderPlainText(ResultData data) {
		String result = "Statement for " + data.getCustomer() + "\n";
		for (Performances performances : data.getPerformances()) {
			result += "  " + performances.getPlays().getName() + ": " + performances.getAmount() / 100 + "��("
					+ performances.getPerf().getAudience() + " seats)\n";
		}
		result += "Amount owed is " + data.getTotalAmount() / 100 + "�� \n";
		result += "You earned " + data.getVolumeCredits() + " credits\n";
		return result;
	}
}
