
public class Player {
	private Playlist[] listaDePlaylists;
	private int numDePlaylists;
	private int numDePlaylistsOcupadas;

	public Player(int numDePlaylists) {
		this.listaDePlaylists = new Playlist[numDePlaylists];
		this.numDePlaylists = numDePlaylists;
		this.numDePlaylistsOcupadas = 0;
	}

	public Playlist[] getListaDePlaylists() {
		return listaDePlaylists;
	}

	public int getNumDePlaylists() {
		return numDePlaylists;
	}

	public int getNumDePlaylistsOcupadas() {
		return numDePlaylistsOcupadas;
	}
	
    public void adicionarPlaylist(Playlist playlist)
    {
    	if (numDePlaylistsOcupadas >= numDePlaylists)
    	{
    		throw new IllegalArgumentException("Não há mais capacidade para playlists");
    	}
    	else
    	{
    		listaDePlaylists[numDePlaylistsOcupadas] = playlist;
    		numDePlaylistsOcupadas++;
    	}
    }
    /*
    public Playlist getPlaylistByIndex(int playlistIndex)
    {
    	if(playlistIndex <0 || playlistIndex > (numDePlaylists -1))
    	{
    		throw new IllegalArgumentException("Esta playlist não existe");
    	}
    	else
    	{
    		return listaDePlaylists[playlistIndex];
    	}
    }
	*/
	public void increasePlaylistArray()
	{
		/*
		 * If the size of the listaDePlaylists array is insufficient to accommodate the new playlist, we copy the array and add one free position
		 */
		Playlist[] listaDePlaylistsAux = new Playlist[numDePlaylists + 1];
		for(int i = 0; i<numDePlaylists; i++)
		{
			listaDePlaylistsAux[i] = listaDePlaylists[i];
		}
		listaDePlaylists = listaDePlaylistsAux;
		numDePlaylists++;
	}
	
	public boolean playlistIndexExists(int playlistIndex)
	{
		if(playlistIndex>=0 && playlistIndex<numDePlaylists && numDePlaylists!=0)
		{
			return true;					
		}			
		return false;
	}
	
	public boolean playlistIndexExistsAndIsNotNull(int playlistIndex)
	{
		if(playlistIndex>=0 && playlistIndex<numDePlaylists && numDePlaylists!=0)
		{
			if(listaDePlaylists[playlistIndex]!= null)
			{
				return true;					
			}
		}			
		return false;
	}
	
	public void deletePlaylist(int playlistIndex){
		if(!playlistIndexExists(playlistIndex))
		{
			throw new IllegalArgumentException("Esta playlist não existe");
			//System.out.println("A playlist seleccionada não existe!");
			//System.out.println("Por favor introduza um número válido.");
		}
		else
		{
			// Is this the last play list? If so remove it.
			if(playlistIndex == (numDePlaylists-1))
			{
				listaDePlaylists[playlistIndex] = null;
			}
			// It's not the last play list, put the last play list in the selected index and remove the last one
			else
			{	
				listaDePlaylists[playlistIndex] = listaDePlaylists[numDePlaylists-1];
				listaDePlaylists[numDePlaylists-1] = null;
			}
			numDePlaylists--;
		}
	}
}
