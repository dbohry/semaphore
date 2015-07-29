public class Semaforo {

	private final Sync sync;

	public Semaforo(int permits) {
		sync = new NonfairSync(permits);
	}

	/**
	 * Notifica as outras Threads que o recurso foi liberado
	 */
	public void release() {
		sync.releaseShared(1);
	}

	/**
	 * Verifica se é possível liberar o recurso
	 * 
	 * @throws InterruptedException
	 */
	public void acquire() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	static final class NonfairSync extends Sync {
		private static final long serialVersionUID = -2694183684443567898L;

		NonfairSync(int permits) {
			super(permits);
		}

		protected int tryAcquireShared(int acquires) {
			return nonfairTryAcquireShared(acquires);
		}
	}

}
