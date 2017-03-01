public class Musica {
    private String titulo;
    private String autor;
    private Double duracao;
    private String estilo;

    public Musica(String titulo, String autor, Double duracao, String estilo) {
        this.titulo = titulo;
        this.autor = autor;
        this.duracao = duracao;
        this.estilo = estilo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    
    @Override
    public String toString() {
        return "Musica [titulo=" + titulo + ", autor=" + autor + ", duracao=" + duracao + ", estilo=" + estilo
                + "]";
    }
}
