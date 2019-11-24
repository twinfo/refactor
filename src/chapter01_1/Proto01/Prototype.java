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
    	//构造剧目数据
        playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
        playsMap.put("as-like", new Plays("comedy", "As You Like It"));
        playsMap.put("othello", new Plays("tragedy", "Othello"));

        //构造账单数据
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
            result += "  " +playFor(perf).getName() + ": " + amountFor(perf) / 100 + "￥(" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + totalAmount() / 100 + "￥ \n";
        result += "You earned " +  totalVolumeCredits() + " credits\n";
        return result;
		// 5.2.a 拆分循环 5.2.c 提炼函数并内联
        //int totalAmount = appleSauce();
       // int totalAmount = 0;
       // for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	//4.从表达式右边提炼函数playFor
        	//Plays play = playFor(perf);
            //1.1 提炼函数：设置函数调用点
           // int thisAmount = amountFor(perf, playFor(perf)); // 4.2 “以查询取代临时变量”
            //4.3.2 将函数接口中的参数删除
        	//int thisAmount = amountFor(perf); //3.4用内联变量方法去除临时变量
           // volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // 4.2 “以查询取代临时变量”
           // if ("comedy".equals(playFor(perf).getType())) volumeCredits += Math.floor(perf.getAudience() / 5);
        	// 5.1 提炼循环体内语句为函数
        	//volumeCredits += volumeCreditsFor(perf); // 5.2拆分循环
        	// 4.2 “以查询取代临时变量”
            //totalAmount += amountFor(perf);
       // }
        // 5.2.a 拆分循环  5.2.c 提炼函数并内联
       // String result = "Statement for " + invoicesMap.get("customer") + "\n";
       // for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
       //     result += "  " +playFor(perf).getName() + ": " + amountFor(perf) / 100 + "￥(" + perf.getAudience() + " seats)\n";
       // }
        // 5.2.a 拆分循环
        //int volumeCredits = 0;
        //for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
       //    	volumeCredits += volumeCreditsFor(perf);
       // }
        // 5.2.b 提炼函数并内联
        //int volumeCredits = totalVolumeCredits();
        //result += "Amount owed is " + totalAmount() / 100 + "￥ \n";
        //result += "You earned " +  totalVolumeCredits() + " credits\n";
        //return result;
    }
	
	// 函数改名为：totalAmount
	//private int appleSauce() {
	private int totalAmount() {
		int result = 0;  // totalAmount 改名为 result
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	result += amountFor(perf);
        }
		return result;
	}

	//5.2.b “提炼函数”并内联
	private int totalVolumeCredits() {
		int result = 0; // volumeCredits 改名为 result
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
        	result += volumeCreditsFor(perf);
        }
		return result;
	}
	
	// 5.1 提炼循环体内语句为函数
	private int volumeCreditsFor(Invoices oPerformance) {
		int result = 0;
		result += Math.max(oPerformance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(oPerformance).getType())) 
        	result += Math.floor(oPerformance.getAudience() / 5);
		return result;
	}

	//3 从表达式右边提炼函数，注意接口变量的命名规则
	private Plays playFor(Invoices oPerformance) {
		return playsMap.get(oPerformance.getPlayID());
	}

	// 1.提炼函数
	// 4.3.1 在amountFor函数内部使用新提炼的函数  play 替换为 playFor(oPerformance)
	// 4.3.2 将函数接口中的参数play删除
	//private int amountFor(Invoices oPerformance, Plays play)  { // 3.函数参数改名：perf -- oPerformance
	private int amountFor(Invoices oPerformance)  {
		//2.变量改名：thisAmount 改名为 result
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

