import java.util.List;
import java.util.Map;

public class ExtratatorDeConteudoDaNasa implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudo(String json) {
        //Extrar só os dados
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
                .toList();
    }
}
