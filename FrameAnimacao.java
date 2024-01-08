import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 31 de Maio de 2021
Alteracao: 04 de Junho de 2021
Nome.....: FrameAnimacao
Funcao...: Classe que tem como objetivo simular a animacao. Herda de JPanel
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class FrameAnimacao extends JPanel {
  private BufferedImage imagem = new BufferedImage(900, 450, BufferedImage.TYPE_INT_BGR);  //imagem na qual um objeto 'Graphic' pode 'desenhar'
  private Display display;                      //referencia a display
  private Pensador[] pensadores;                //referencia aos pensadores
  private Garfo[] garfos;                       //referencia aos garfos
  private boolean iniciado = false;             //boolean para iniciar
  private Sprite mesa, prato, bisteca, fundo;   //sprites de objetos a serem desenhados

  /* *********************
  * Metodo: FrameAnimacao
  * Funcao: Construtor
  * Parametros: Display display, Pensador[] pensadores, Garfo[] garfos
  ********************* */
  public FrameAnimacao(Display display, Pensador[] pensadores, Garfo[] garfos){
    this.display = display;
    this.pensadores = pensadores;
    this.garfos = garfos;

    mesa = new Sprite("res/mesa.png");
    prato = new Sprite("res/prato.png");
    bisteca = new Sprite("res/bisteca.png");
    fundo = new Sprite("res/background.png");

    setSize(new Dimension(900, 450));
    add(display.getTela(), BorderLayout.CENTER);
  }

  /* *********************
  * Metodo: renderizar
  * Funcao: 'desenha' na tela atraves de objetos criados a partir de display
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void renderizar(){
    //inicia as threads
    if (!iniciado){
      pensadores[0].start();
      pensadores[1].start();
      pensadores[2].start();
      pensadores[3].start();
      pensadores[4].start();
      iniciado = true;
    }

    while (true){
      //criacao do objeto de desenho "Graphics 'g'"------------
      display.criarBufferStrategy();
      BufferStrategy bs = display.getTela().getBufferStrategy();
      if (bs == null) {
        display.criarBufferStrategy();
        bs = display.getTela().getBufferStrategy();
      }
      Graphics g = imagem.getGraphics();
      g = bs.getDrawGraphics();
      //Desenho dos graficos em si-----------------------------

      //desenhos fixos
      g.drawImage(fundo.getSprite(), -5, -20, null);
      g.drawImage(mesa.getSprite(), 300, 70, 300, 300, null);

      g.drawImage(prato.getSprite(), 350, 235, 80, 80, null);
      g.drawImage(prato.getSprite(), 340, 140, 80, 80, null);
      g.drawImage(prato.getSprite(), 410, 100, 80, 80, null);
      g.drawImage(prato.getSprite(), 480, 140, 80, 80, null);
      g.drawImage(prato.getSprite(), 470, 235, 80, 80, null);

      //desenho da bisteca no prato
      if (pensadores[0].getEstado() != Pensador.COMENDO)
        g.drawImage(bisteca.getSprite(), 370, 255, 40, 40, null);
      if (pensadores[1].getEstado() != Pensador.COMENDO)
        g.drawImage(bisteca.getSprite(), 360, 160, 40, 40, null);
      if (pensadores[2].getEstado() != Pensador.COMENDO)
        g.drawImage(bisteca.getSprite(), 430, 120, 40, 40, null);
      if (pensadores[3].getEstado() != Pensador.COMENDO)
        g.drawImage(bisteca.getSprite(), 500, 160, 40, 40, null);
      if (pensadores[4].getEstado() != Pensador.COMENDO)
        g.drawImage(bisteca.getSprite(), 490, 255, 40, 40, null);

      //desenho dos garfos na mesa
      if (garfos[0].estaDisponivel())
        g.drawImage(garfos[0].getImagem().getSprite(), 410, 235, 60, 60, null);
      if (garfos[1].estaDisponivel())
        g.drawImage(garfos[1].getImagem().getSprite(), 345, 190, 60, 60, null);
      if (garfos[2].estaDisponivel())
        g.drawImage(garfos[2].getImagem().getSprite(), 375, 120, 60, 60, null);
      if (garfos[3].estaDisponivel())
        g.drawImage(garfos[3].getImagem().getSprite(), 445, 120, 60, 60, null);
      if (garfos[4].estaDisponivel())
        g.drawImage(garfos[4].getImagem().getSprite(), 475, 190, 60, 60, null);

      //desenho dos pensadores
      g.drawImage(pensadores[0].getImagens()[pensadores[0].getEstado()].getSprite(), 290, 250, 100, 100, null);
      g.drawImage(pensadores[1].getImagens()[pensadores[1].getEstado()].getSprite(), 260, 100, 100, 100, null);
      g.drawImage(pensadores[2].getImagens()[pensadores[2].getEstado()].getSprite(), 400, 20, 100, 100, null);
      g.drawImage(pensadores[3].getImagens()[pensadores[3].getEstado()].getSprite(), 540, 100, 100, 100, null);
      g.drawImage(pensadores[4].getImagens()[pensadores[4].getEstado()].getSprite(), 510, 250, 100, 100, null);

      //--------------------------------------------------------
      g.dispose(); //acaba com o objeto 'g' tirando sua referencia
      bs.show();   //torna o buffer, onde eh desenhado, visivel
    }
  }
}
