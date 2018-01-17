public class FindSubString{
	public static void main(String[] args){
		String source="abcdefghijk";
		String sub="bcd";
		posOfSubString(source,sub);
	}
	
	private static int posOfSubString(String source,String sub){
		int pos=-1;
		for(int i=0;i<source.length();i++){
			System.out.println("==="+source.charAt(i));
			
			char x=source.charAt(i);
			
			for(int j=0;j<sub.length;j++){
				char y=sub.charAt(j);
				
				
			}
			
		}
		return pos;
	}
}