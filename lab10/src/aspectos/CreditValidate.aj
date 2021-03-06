package aspectos;

import contas.Conta;

public aspect CreditValidate {
	void around(Conta account, double credit): target(account) && call(void creditar(double)) && args(credit) {
        double saldo = account.getSaldo();

        proceed(account, credit);

        double saldo_post = account.getSaldo();

        if (saldo + credit != saldo_post) {
            throw new IllegalStateException("Credit has NOT been added properly.");
        }
        else {
        	System.out.println("Credit HAS BEEN added properly.");
        }
    }
}
