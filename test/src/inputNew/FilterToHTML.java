package inputNew;

public class FilterToHTML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    //从excel读出之后，用于structure中的较长元素
	public static String filter(String a)
	{
		//{"target":"course","action":"add","value":{"university":"saos","course":
		//{"structure":[{"category":"' \" & ^ * % $ # @ ! ~ < > \\","summary":"' \" & ^ * % $ # @ ! ~ &lt; &gt; \\<br>"}]}}}
		String result=a;
		result.replace(">", "&gt;");
		result.replace("<", "&lt;");
		result.replace("/r/n", "<br>");
		result.replace("/n", "<br>");
		
		return result;
		
	}
}
