package chapter01_1.Proto04;

public class Prototype {
	public static void main(String[] args) {
		Prototype prototype = new Prototype();
		System.out.println(prototype.statement());
		System.out.println(prototype.htmlStatement());
	}

	//准备数据，传递给renderPlainText
	public String statement() {
		return renderPlainText(DataProcessBO.getInstance().createResultData());
	}
	
	public String htmlStatement() {
		return  renderHtmlstatement(DataProcessBO.getInstance().createResultData());
	}

	// 替换原有调用点
	private String renderPlainText(ResultData data) { 
		String result = "Statement for " + data.getCustomer() + "\n";
		for (Performances performances : data.getPerformances()) {
			result += "  " + performances.getPlays().getName() + ": " + performances.getAmount() / 100 + "￥("
					+ performances.getPerf().getAudience() + " seats)\n";
		}
		result += "Amount owed is " + data.getTotalAmount() / 100 + "￥ \n";
		result += "You earned " + data.getVolumeCredits() + " credits\n";
		return result;
	}
	
	private String renderHtmlstatement(ResultData data) {
        String result = "<h1>Statement for " + data.getCustomer() + "</h1>\n";
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n";
        for (Performances performances : data.getPerformances()) {
            result += "<tr><td>" + performances.getPlays().getName() + "</td><td>" + performances.getPerf().getAudience() + "</td>";
            result += "<td>" + performances.getAmount() / 100 + "￥</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>" + data.getTotalAmount()/ 100  + "￥</em></p>\n";
        result += "<p>You earned <em>" + data.getVolumeCredits() + "</em> credits</p>\n";
        return result;
    }

}
