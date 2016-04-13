import java.text.SimpleDateFormat;
import java.util.*;


public class SiteAge {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
		ArrayList<String> list=new ArrayList<String>();
		while(true)
		{
			String row=in.nextLine();
			if(row.equals("#"))
			{
				for(int i=0;i<list.size();i++)
				{
					System.out.println(list.get(i));
				}
				in.close();
				return;
			}
			else
			{
				String newDate=toDate(Integer.parseInt(row));
				list.add(newDate);
			}
		}
		
	}
	public static String toDate(int day)
	{
		Calendar c = Calendar.getInstance();   
        int date=c.get(Calendar.DATE);   /*
        System.out.println("今天是"+year+"年"+month+"月"+date+"日");   
        System.out.println("是今年的第"+c.get(Calendar.DAY_OF_YEAR)+"天");   
        System.out.println("c.getTime()的結果: "+c.getTime());   
        System.out.println("new Date()的結果: "+new Date()); */  
        c.set(Calendar.DAY_OF_YEAR, date -day);   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        String time=sdf.format(c.getTime()); 
        //System.out.println("2天前是"+time); 
		return time;
	}

}
