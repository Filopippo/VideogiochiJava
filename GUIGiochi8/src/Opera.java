import java.util.Comparator;

public abstract class Opera implements Votabile{
	protected double voto;
	protected String nome="";
	
	
	
	public double getVoto() {
		return voto;
	}


	public void setVoto(double voto) {
		this.voto = voto;
	}
	
	public String getNome() {
	return nome;
}
	
	public String toString() {
		return nome;
	}


	public void setNome(String nome) {
	this.nome = nome;
}
	
	public static Comparator<Opera> NameComparator = new Comparator<Opera>() {

		public int compare(Opera s1, Opera s2) {
		   String Name1 = s1.getNome().toUpperCase();
		   String Name2 = s2.getNome().toUpperCase();

		   //ascending order
		   return Name1.compareTo(Name2);

	    }};
	    
		public static Comparator<Opera> VoteComparator = new Comparator<Opera>() {

			public int compare(Opera s1, Opera s2) {
			   double voto1 = s1.getVoto();
		
			   double voto2 = s2.getVoto();
			   //ascending order
			   if (voto1==voto2)
				   return 0;
			   else if (voto1>voto2)
				   return -1;
			   else 
				   return 1;


		    }};
	
}
