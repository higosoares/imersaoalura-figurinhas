public enum API {
    //String imdbKey = System.getenv("IMDB_API_KEY");
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorDeConteudoDoIMDB()),
    IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json", new ExtratorDeConteudoDoIMDB()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json", new ExtratatorDeConteudoDaNasa());
    private String url;
    private ExtratorDeConteudo extrator;
    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }
}
