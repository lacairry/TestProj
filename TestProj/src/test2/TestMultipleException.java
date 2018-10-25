package test2;

public class TestMultipleException {

	  static class FirstException extends Exception {
		  @Override
		  public String toString(){
			  return "First";
		  }
	  }
	  static class SecondException extends Exception { 
		  @Override
		  public String toString(){
			  return "Second";
		  }

	  }
	  
	  private class MyClass{
		  
	  }

	  public void rethrowException(String exceptionName) throws FirstException, SecondException {
	    try {
	      if (exceptionName.equals("First")) {
	        throw new FirstException();
	      } else {
	        throw new SecondException();
	      }
	    } catch (Exception e) {
	      throw e;
	    }
	  }
	  
	  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exception e1 = new TestMultipleException.FirstException();
		Exception e2 = new TestMultipleException.SecondException();
		try{
			new TestMultipleException().rethrowException("ABC");
		}catch (FirstException|SecondException e) {
		      System.err.println(e);
		    }
		
		MyClass mc = new TestMultipleException().new MyClass();
		
	}

}
