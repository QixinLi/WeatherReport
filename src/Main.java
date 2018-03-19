import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("citydata.txt");
	    try{
	    	BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	    	String s = null;
	    	while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	    		Start.mycity.add(new Weather(s));
	    	}
	    	br.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    if(Start.mycity.isEmpty())
	    {
	    	new qtcs();
	    }
	    else{
	    	new Start(0);
	    }
		
	}

}
