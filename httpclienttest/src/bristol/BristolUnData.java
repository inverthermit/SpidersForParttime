package bristol;
import java.util.*;
import java.util.Comparator;
import java.util.regex.*;

public class BristolUnData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//<div id="course-structure" class="module">
		//id=typical-offer
		//A 7.5 7  B D 7.0 7 C 6.5 6.5    E 6.5 6 F 6 6 Profile Foundation 6.0 5.5
		//Undergraduate fee:Arts courses 15200  Science courses 18300  Clinical courses 33900
		//Postgraduate fee using EL pattern
		Pattern p = Pattern.compile("[0-9]+,[0-9]+");
		Matcher m = p.matcher("<dl><dt>UK/EU</dt><dd>£4,145</dd><dt>Overseas</dt><dd>£14,200</dd></dl>");
		ArrayList<Integer> money=new ArrayList<Integer>();
		while (m.find()) {
			money.add(Integer.parseInt(m.group().replace(",", "")));
		}
		int max=0;
		for(int w=0;w<money.size();w++)
		{
			if(money.get(w)>max)
			{
				max=money.get(w);
			}
		}
		System.out.println(max);
		
	}

}
