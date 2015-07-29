public class ProcessadorThread extends Thread {
	private int idThread;

	private Semaforo semaforo;

	public ProcessadorThread(int id, Semaforo semaforo) {
		this.idThread = id;
		this.semaforo = semaforo;
	}

	/**
	 * apenas simulo um processo longo (sleep) para n�o ser t�o rapido
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
	 * esse m�todo simula o acesso da Thread � uma regi�o n�o cr�tica (sem
	 * necessidade de pedir trava)
	 */
	private void entrarRegiaoNaoCritica() {
		System.out.println("Thread #" + idThread + " em regi�o n�o cr�tica");
		processar();
	}

	/**
	 * esse m�todo simula o acesso da Thread em uma regi�o cr�tica, e ser�
	 * chamado ap�s conseguir a trava do sem�foro
	 */
	private void entrarRegiaoCritica() {
		System.out.println("Thread #" + idThread
				+ " entrando em regi�o cr�tica");
		processar();
		System.out.println("Thread #" + idThread + " saindo da regi�o cr�tica");
	}

	/**
	 * nesse m�todo realizamos um processamento n�o cr�tico, depois requisitamos
	 * o acesso ao sem�foro e em seguida realizamos o processo de uma regi�o
	 * cr�tica.
	 */
	public void run() {
		entrarRegiaoNaoCritica();
		try {
			semaforo.acquire();
			entrarRegiaoCritica();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// libera o recurso do sem�foro
			semaforo.release();
		}
	}
}
