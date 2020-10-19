import java.util.Comparator;

public class Videogioco extends Opera {
private Platform platform=Platform.Playstation;
	public Videogioco (String nome, double voto) {
		this.nome=nome;
		this.voto=voto;
	}
	
	public Videogioco (String nome, double voto, Platform p) {
		this.nome=nome;
		this.voto=voto;
		setPlatform(p);
	}
	
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
}

	public boolean equals(Object o) {
		if (o instanceof Videogioco) {
		Videogioco p = (Videogioco)o;
		return (p.nome.toUpperCase().equals(this.nome.toUpperCase()) && p.platform == this.platform);
	}
return false;
	
}
	
	
	public String inStringa() {
		return (this.nome + "\t" + this.voto + "\t" + this.platform);
	}
	
}
