public class  RotateString{
	private static char[] str={'a','b','c','d','e','f','g'};
	
	public static void main(String[] args){
		for(int i=0;i<str.length;i++){
			System.out.print(" "+str[i]);
		}
		System.out.println();
		rotateStr(str,3);
		for(int i=0;i<str.length;i++){
			System.out.print(" "+str[i]);
		}
	}
	
	private static void rotateStr(char[] str,int offset){
		if(str.length<=0||offset<=0){
			return;
		}
		
		int len=str.length;
		int high=len-1;
		for(int i=1;i<=offset%len;i++){
			char temp=str[high];
			for(int j=high-1;j>=0;j--){
				str[j+1]=str[j];
			}
			str[0]=temp;
		}
	}
}

