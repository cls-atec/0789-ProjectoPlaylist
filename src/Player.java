
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

    public void increasePlaylistArray()
    {
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
