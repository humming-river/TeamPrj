import java.awt.Graphics;

public class Timer extends Entity{
		//60�ʺ��� �����ؼ� 1�ʾ� ��Ƴ�����
		//0�ʰ� �Ǵ� ���� Timeout
		//�ʿ��� �� : for��(�ݺ��ؼ� �ð�����),������, sleep�Լ�	
		//ĵ����,������ ���� �������(�켱�� ���⸸)
		
		//walktempo ����
	
	
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
} //������Ʈ

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
