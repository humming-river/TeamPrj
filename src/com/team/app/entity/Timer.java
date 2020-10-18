import java.awt.Graphics;

public class Timer extends Entity{
		//60초부터 시작해서 1초씩 깎아내려감
		//0초가 되는 순간 Timeout
		//필요한 것 : for문(반복해서 시간감소),스레드, sleep함수	
		//캔버스,프레임 만들어서 띄워보기(우선은 띄우기만)
		
		//walktempo 참고
	
	
	int num;
	public Timer(int time) {
		for(int i = 0; i<60 ; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time--;
			num = time;
			System.out.println(num);
			}	
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}	
} //업데이트

//   public Timer(int time) {
//      for(int i = 0; i<time ; i++) {
//         try {
//            Thread.sleep(1000);
//         } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//         }
//  
//         
//         System.out.println(time);
//         this.time = time--;
