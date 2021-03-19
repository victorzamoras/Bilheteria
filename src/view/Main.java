package view;

	import java.util.concurrent.Semaphore;

	import controller.BilheteriaController;

	public class Main {

		public static void main(String[] args) {		
			final int permissao = 1;
			Semaphore semaforo = new Semaphore(permissao);
			for (int id = 1; id <= 300; id++) {
				Thread t = new BilheteriaController(id, semaforo);
				t.start();
			}
		}
	}
