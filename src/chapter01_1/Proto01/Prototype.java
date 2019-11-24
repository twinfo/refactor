package chapter01_1.Proto01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prototype {
    private Map<String, Plays> playsMap = new HashMap<>();
    private Map<String, Object> invoicesMap = new HashMap<>();
    public static void main(String[] args) {
    	Prototype prototype = new Prototype();
    	prototype.initData();
        System.out.println(prototype.statement());
    }

    public void initData() {
    	//�����Ŀ����
        playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
        playsMap.put("as-like", new Plays("comedy", "As You Like It"));
        playsMap.put("othello", new Plays("tragedy", "Othello"));

        //�����˵�����
        invoicesMap.put("customer", "BigCo");
        List<Invoices> invoicesList = Arrays.asList(
                new Invoices("hamlet", 55),
                new Invoices("as-like", 35),
                new Invoices("othello", 40));
        invoicesMap.put("performances", invoicesList);
	}

	public String statement() {
		String result = "Statement for " + invoicesMap.get("customer") + "\n"; 
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            result += "  " +playFor(perf).getName() + ": " + amountFor(perf) / 100 + "��(" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + totalAmount() / 100 + "�� \n";
        result += "You earned " +  totalVolumeCredits() + " credits\n";
        return result;
		// 5.2.a ���ѭ�� 5.2.c ��������������
        //int totalAmount = appleSauce();
       // int totalAmount = 0;
       // for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	//4.�ӱ��ʽ�ұ���������playFor
        	//Plays play = playFor(perf);
            //1.1 �������������ú������õ�
           // int thisAmount = amountFor(perf, playFor(perf)); // 4.2 ���Բ�ѯȡ����ʱ������
            //4.3.2 �������ӿ��еĲ���ɾ��
        	//int thisAmount = amountFor(perf); //3.4��������������ȥ����ʱ����
           // volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // 4.2 ���Բ�ѯȡ����ʱ������
           // if ("comedy".equals(playFor(perf).getType())) volumeCredits += Math.floor(perf.getAudience() / 5);
        	// 5.1 ����ѭ���������Ϊ����
        	//volumeCredits += volumeCreditsFor(perf); // 5.2���ѭ��
        	// 4.2 ���Բ�ѯȡ����ʱ������
            //totalAmount += amountFor(perf);
       // }
        // 5.2.a ���ѭ��  5.2.c ��������������
       // String result = "Statement for " + invoicesMap.get("customer") + "\n";
       // for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
       //     result += "  " +playFor(perf).getName() + ": " + amountFor(perf) / 100 + "��(" + perf.getAudience() + " seats)\n";
       // }
        // 5.2.a ���ѭ��
        //int volumeCredits = 0;
        //for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
       //    	volumeCredits += volumeCreditsFor(perf);
       // }
        // 5.2.b ��������������
        //int volumeCredits = totalVolumeCredits();
        //result += "Amount owed is " + totalAmount() / 100 + "�� \n";
        //result += "You earned " +  totalVolumeCredits() + " credits\n";
        //return result;
    }
	
	// ��������Ϊ��totalAmount
	//private int appleSauce() {
	private int totalAmount() {
		int result = 0;  // totalAmount ����Ϊ result
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	result += amountFor(perf);
        }
		return result;
	}

	//5.2.b ������������������
	private int totalVolumeCredits() {
		int result = 0; // volumeCredits ����Ϊ result
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	result += volumeCreditsFor(perf);
        }
		return result;
	}
	
	// 5.1 ����ѭ���������Ϊ����
	private int volumeCreditsFor(Invoices oPerformance) {
		int result = 0;
		result += Math.max(oPerformance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(oPerformance).getType())) 
        	result += Math.floor(oPerformance.getAudience() / 5);
		return result;
	}

	//3 �ӱ��ʽ�ұ�����������ע��ӿڱ�������������
	private Plays playFor(Invoices oPerformance) {
		return playsMap.get(oPerformance.getPlayID());
	}

	// 1.��������
	// 4.3.1 ��amountFor�����ڲ�ʹ���������ĺ���  play �滻Ϊ playFor(oPerformance)
	// 4.3.2 �������ӿ��еĲ���playɾ��
	//private int amountFor(Invoices oPerformance, Plays play)  { // 3.��������������perf -- oPerformance
	private int amountFor(Invoices oPerformance)  {
		//2.����������thisAmount ����Ϊ result
		int result = 0;
		switch (playFor(oPerformance).getType()) { // 4.3.1
		    case "tragedy":
		        result = 40000;
		        if (oPerformance.getAudience() > 30) {
		            result += 1000 * (oPerformance.getAudience() - 30);
		        }
		        break;
		    case "comedy":
		        result = 30000;
		        if (oPerformance.getAudience() > 20) {
		            result += 10000 + 500 * (oPerformance.getAudience() - 20);
		        }
		        result += 300 * oPerformance.getAudience();
		        break;
		    default:
		        throw new Error("unknown type");
		}
		return result;
	}

}

