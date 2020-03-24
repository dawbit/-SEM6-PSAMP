package contas;

/**
 * Classe que modela objetos que representam contas de um banco.
 */
public class Conta {

	// representa o n�mero da conta
	private String numero;

	// representa o saldo da conta
	private double saldo;

	/**
	 * Construtor que recebe um String para inicializar o
	 * atributo n�mero e um double para inicializar o saldo.
	 */
	public Conta(String numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	// sets credit value instead of = operation
	private void setCreditar(double valor) {
		this.saldo += valor;
	}
	
	private void setDebitar(double valor) {
		this.saldo -= valor;
	}
	
	/**
	 * Construtor que recebe um String para inicializar o
	 * atributo n�mero. O saldo � inicializado com zero.
	 */
	public Conta(String numero) {
		this(numero, 0.0);
	}

	/**
	 * Retorna o valor do atributo numero do objeto.
	 */
	public String getNumero() {
		return this.numero;
	}

	/**
	 * Retorna o valor do atributo saldo do objeto.
	 */
	public double getSaldo() {
		return this.saldo;
	}

	/**
	 * Soma ao saldo um valor recebido como par�metro
	 */
	public void creditar(double valor) {
		this.setCreditar(valor);
	}

	public void debitar(double valor) throws SaldoInsuficienteException {
		if (valor > saldo) {
			throw new SaldoInsuficienteException(saldo, numero);
		} else {
			this.setDebitar(valor);
		}
	}

}