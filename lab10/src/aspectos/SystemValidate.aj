package aspectos;

public aspect SystemValidate {
	declare warning: 
		set(private * *) 
		&& !withincode(* set*(..)) 
		&& !withincode(*.new(..)) 
		&& !withincode(* contas.RepositorioContas.*(..)) 
		&& !withincode(* contas.RepositorioContasArray.*(..))
		&& !withincode(* contas.RepositorioContasLista.*(..)):
			"bad field set";
}
