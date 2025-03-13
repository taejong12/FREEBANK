package main;

public class Test {
	public static void main(String[] args) {
		String num = "01011112222";
		String aaa = "";
		for(int i=0;i<num.length();i++) {
			aaa += num.charAt(i);
			if(i==2 || i==6) {
				aaa += "-";
			}
			
		}
		
		System.out.println(aaa);
	}
}
