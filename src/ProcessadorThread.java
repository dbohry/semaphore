public class ProcessadorThread extends Thread {
	private int idThread;

	private Semaforo semaforo;

	public ProcessadorThread(int id, Semaforo semaforo) {
		this.idThread = id;
		this.semaforo = semaforo;
	}

	/**
	 * apenas simulo um processo longo (sleep) para não ser tão rapido
	 */
	private void processar() {
		try {
			System.out.println("Thread #" + idThread + " processando");
			Thread.sleep((long) (Math.random() * 10000));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * esse método simula o acesso da Thread à uma região não crítica (sem
	 * necessidade de pedir trava)
	 */
	private void entrarRegiaoNaoCritica() {
		System.out.println("Thread #" + idThread + " em região não crítica");
		processar();
	}

	/**
	 * esse método simula o acesso da Thread em uma região crítica, e será
	 * chamado após conseguir a trava do semáforo
	 */
	private void entrarRegiaoCritica() {
		System.out.println("Thread #" + idThread
				+ " entrando em região crítica");
		processar();
		System.out.println("Thread #" + idThread + " saindo da região crítica");
	}

	/**
	 * nesse método realizamos um processamento não crítico, depois requisitamos
	 * o acesso ao semáforo e em seguida realizamos o processo de uma região
	 * crítica.
	 */
	public void run() {
		entrarRegiaoNaoCritica();
		try {
			semaforo.acquire();
			entrarRegiaoCritica();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// libera o recurso do semáforo
			semaforo.release();
		}
	}
}
