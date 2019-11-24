package chapter01_1.Proto02;

import java.util.ArrayList;
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
		// 构造剧目数据
		playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
		playsMap.put("as-like", new Plays("comedy", "As You Like It"));
		playsMap.put("othello", new Plays("tragedy", "Othello"));

		// 构造账单数据
		invoicesMap.put("customer", "BigCo");
		List<Invoices> invoicesList = Arrays.asList(new Invoices("hamlet", 55), new Invoices("as-like", 35),
				new Invoices("othello", 40));
		invoicesMap.put("performances", invoicesList);
	}

	public String statement() {
		// 6.1 拆分阶段：提炼函数
		ResultData data = new ResultData();
		List<Performances> performances = createPerformancesData();
		data.setPerformances(performances);
		data.setCustomer((String) invoicesMap.get("customer"));
		return renderPlainText(data);
	}

	private String renderPlainText(ResultData data) {
		String result = "Statement for " + data.getCustomer() + "\n";
		for (Performances performances : data.getPerformances()) {
			result += "  " + performances.getPlays().getName() + ": " + performances.getAmount() / 100 + "￥("
					+ performances.getPerf().getAudience() + " seats)\n";
		}
		result += "Amount owed is " + totalAmount() / 100 + "￥ \n";
		result += "You earned " + totalVolumeCredits() + " credits\n";
		return result;
	}

	private List<Performances> createPerformancesData() {
		List<Performances> performancesList = new ArrayList<>();
		for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
			performancesList.add(new Performances(playsMap.get(perf.getPlayID()), perf, amountFor(perf), 0));
		}
		return performancesList;

	}

	private int totalAmount() {
		int result = 0;
		for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
			result += amountFor(perf);
		}
		return result;
	}

	private int totalVolumeCredits() {
		int result = 0;
		for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
			result += volumeCreditsFor(perf);
		}
		return result;
	}

	private int volumeCreditsFor(Invoices oPerformance) {
		int result = 0;
		result += Math.max(oPerformance.getAudience() - 30, 0);
		if ("comedy".equals(playFor(oPerformance).getType()))
			result += Math.floor(oPerformance.getAudience() / 5);
		return result;
	}

	private Plays playFor(Invoices oPerformance) {
		return playsMap.get(oPerformance.getPlayID());
	}

	private int amountFor(Invoices oPerformance) {
		int result = 0;
		switch (playFor(oPerformance).getType()) {
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
