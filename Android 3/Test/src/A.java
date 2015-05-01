import java.util.ArrayList;
import java.util.Arrays;


public class A {

	public static void main(String[] args) {
		String a = "";
		String []temp=a.split(",");
		for(String t:temp)
		System.out.println(t.length());
		ArrayList<String> answerList = new ArrayList<String>(
				Arrays.asList(temp));
		System.out.println(answerList.size());
		answerList.remove("");
		System.out.println(answerList.size());

	}

}
