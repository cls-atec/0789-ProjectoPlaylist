public class Playlist {
	private String nome;
	private int capacidade;
	private Musica listaMusicas[];
	private int numActualMusicas;
	
	public Playlist(String nome, int capacidade) {
		this.nome = nome;
		this.capacidade = capacidade;
		this.listaMusicas = new Musica[capacidade];
		this.numActualMusicas = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public Musica[] getListaMusicas() {
		return listaMusicas;
	}

	public int getNumActualMusicas() {
		return numActualMusicas;
	}
	
	@Override
	public String toString() {
		String texto = "Playlist [nome=" + nome +  ", capacidade=" + capacidade + ", numActualMusicas=" + numActualMusicas +  "]\n";
		texto += "Lista das Musicas\n";
		for(int i = 0; i < numActualMusicas; i++)
		{
			texto += "======================\n";
			texto += "Música " + (i+1) + "\n";
			texto += "Título: " + listaMusicas[i].getTitulo() + "\n";
			texto += "Autor: " + listaMusicas[i].getAutor() + "\n";
			texto += "Duracao: " + listaMusicas[i].getDuracao() + "\n";	
			texto += "Estilo: " + listaMusicas[i].getEstilo() + "\n";
		}
		return texto;
	}
	
	public void adicionaMusica(Musica musica)
	{
		if (numActualMusicas < capacidade)
		{	
			listaMusicas[numActualMusicas] = musica;
			numActualMusicas++;
		}
		else
		{
		    throw new IllegalArgumentException("Playlist cheia");
		}
	}
	
	public void removeMusica(int musicIndex)
	{
		if(musicIndex > (numActualMusicas - 1) || musicIndex < 0 )
		{
			throw new IllegalArgumentException("Musica não existente!");
		}
		else
		{
			// Is this the last play list? If so remove it.
			if(musicIndex == (numActualMusicas-1))
			{
				listaMusicas[musicIndex] = null;
			}
			// It's not the last play list, put the last play list in the selected index and remove the last one
			else
			{	
				listaMusicas[musicIndex] = listaMusicas[numActualMusicas-1];
				listaMusicas[numActualMusicas-1] = null;
			}
			numActualMusicas--;
		}
	}
	//===============================================================
	// Mêtodos adicionais / Other methods
	//===============================================================
	public void increaseMusicArray()
	{
		Musica[] listaDeMusicasAux = new Musica[numActualMusicas+1];
		for(int i = 0; i<numActualMusicas; i++)
		{
			listaDeMusicasAux[i] = listaMusicas[i];
		}
		listaMusicas = listaDeMusicasAux;
		capacidade++;
	}
	
	public boolean musicIndexExists(int musicIndex)
	{
		if(musicIndex>=0 && musicIndex<numActualMusicas && numActualMusicas!=0)
		{
			return true;					
		}			
		return false;
	}
}
