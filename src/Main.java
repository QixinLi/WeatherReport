import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("citydata.txt");
	    try{
	    	BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
	    	String s = null;
	    	while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
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
