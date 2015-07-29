public class Main {
	public static void main(String[] args) {
		int numeroDePermicoes = 2;
		int numeroDeProcessos = 6;
		Semaforo semaforo = new Semaforo(numeroDePermicoes);
		ProcessadorThread[] processos = new ProcessadorThread[numeroDeProcessos];
		for (int i = 0; i < numeroDeProcessos; i++) {
			processos[i] = new ProcessadorThread(i, semaforo);
			processos[i].start();
		}
	}
}
