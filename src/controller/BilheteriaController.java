package controller;

import java.util.concurrent.Semaphore;

public class BilheteriaController extends Thread {
	static int ingressos = 100;
	private int id;
	private Semaphore semaforo;
	private int tempo;
	private int ningressos;

	public BilheteriaController(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (Login()) {
			if (Compra()) {
				try {
					semaforo.acquire();
					validacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}

	}

	public boolean Login() {
		tempo = (int) (Math.random() * 1951 + 50);
		try {
			if (tempo > 1000) {
				System.out.println("Seu login " + id + " deu errado e não poderá comprar");
				return false;
			}
			sleep(tempo);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean Compra() {
		tempo = (int) (Math.random() * 2001 + 1000);
		try {
			if (tempo > 2500) {
				System.out.println(
						"Seu tempo de entrada na sessão " + id + " foi excedido e não poderá efeturar a compra");
				return false;
			}
			sleep(tempo);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validacao() {
		ningressos = (int) ((Math.random() * 4) + 1);
		if (ingressos >= ningressos) {
			System.out.println(id + " comprou " + ningressos + " ingressos");
			ingressos -= ningressos;
			System.out.println("Restam " + ingressos + " ingressos");
		} else
			System.out.println("O numero de ingressos pedidos é maior que o estoque");
	}
}
