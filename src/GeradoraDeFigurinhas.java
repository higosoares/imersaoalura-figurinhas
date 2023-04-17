import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0 , null);

        //Configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 80);//Font.SANS_SERIF
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escreer uma frase
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(texto, posicaoTextoX , posicaoTextoY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);

        //escrever nova imagem em um arquivo
        //Se o diretorio não existir
        var diretorio = new File("imagens/");
        diretorio.mkdir();

        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
