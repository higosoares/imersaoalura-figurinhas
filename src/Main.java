import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //Faz a conexão e busca os filmes
        API api = API.IMDB_TOP_SERIES;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http  = new ClienteHttp();
        String json = http.buscaDados(url);
        
        //Exibe e manipula os dados
        List<Conteudo> conteudos = extrator.extraiConteudo(json);

        var geradora = new GeradoraDeFigurinhas();

        for (Conteudo conteudo : conteudos) {
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "imagens/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}