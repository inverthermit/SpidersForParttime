import java.util.*;
public class wefw {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 100; i++) {  
            Thread.sleep(50);
            processbarshow(i,100); 
        }  
  
    }  
  
	private static int count=1;  
    private static boolean isStart=false;  
      
    public static void processbarshow(int num,int total)  
    {  
        /** 
         * 总共显示30个 
         * ______________________________ 
         * ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 
         */         
        int process=num*30/total;  
        if(!isStart){  
            System.out.println("_____________________________");  
            isStart=true;  
        }  
        if(count>30){  
            count=1;  
            isStart=false;  
        }         
        if(process==count){  
            System.out.print("■");  
            count++;  
        }  
        if(process==30){  
            System.out.println();  
        }  
    }    
	/*
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner in=new Scanner(System.in);
		ArrayList<String> list=new ArrayList<String>();
		while(true)
		{
			String row=in.nextLine();
			if(row.equals("0"))
			{
				for(int i=0;i<list.size();i++)
				{
					System.out.println(list.get(i));
				}
				return;
			}
			else
			{
				String newDate=tDate(row);
				list.add(newDate);
			}
		}
	}
	public static String tDate(String row) throws Exception{
		String result="";
		String[] sp=row.split("/");
		String mon="";
		String day="";
		if(Integer.parseInt(sp[1])<10)
		{
			mon="0"+sp[1];
		}
		else
		{
			mon=sp[1];
		}
		
		if(Integer.parseInt(sp[2])<10)
		{
			day="0"+sp[2];
		}
		else
		{
			day=sp[2];
		}
		
		if(sp[0].equals("")||mon.equals("")||day.equals(""))
			 throw new Exception("aaa");
		result=sp[0]+"-"+mon+"-"+day;
		return result;
	}*/

}
