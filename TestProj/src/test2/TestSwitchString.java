package test2;

public class TestSwitchString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strs = {"ABC","BBC","CBC"};
		for(String str:strs){
			switch(str){
			case "ABC":
				System.out.println("Printing ABC");
				break;
			case "BBC":
				System.out.println("Printing BBC");
				break;
			default:
					System.out.println("Printing CBC");
					break;
			}
		}
	}

}
