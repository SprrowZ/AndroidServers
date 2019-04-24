package android;

public class MyThread extends Thread{
@Override
public void run() {
	// TODO Auto-generated method stub
	System.out.println(LazySingleton.getInstance().hashCode());
}
public static void main(String[] args) {
	MyThread[] threads=new MyThread[100];
    for(int i=0;i<threads.length;i++) {
    	threads[i]=new MyThread();
    }
	for(int i=0;i<threads.length;i++) {
		threads[i].start();
	}
}
}
