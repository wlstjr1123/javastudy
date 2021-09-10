package prob2;

public class SmartPhone extends MusicPhone {
	public void execute ( String function ) {
	      if (function.equals("앱") ) {
	          AppStart();
	          return;
	      }
	      
	      super.execute( function );
	}

	  private void AppStart(){
	    System.out.println("앱 실행");
	  }

}
