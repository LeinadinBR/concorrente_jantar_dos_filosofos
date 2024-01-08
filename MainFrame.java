import java.awt.Dimension;
import javax.swing.JFrame;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 30 de Maio de 2021
Alteracao: 05 de Junho de 2021
Nome.....: MainFrame
Funcao...: Classe que serve de frame para a parte grafica
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class MainFrame extends JFrame {
  private Display display;              //objeto de display
  private Controlador controle;         //objeto de controlador
  private Pensador[] pensadores;        //arranjo de pensadores
  private Garfo[] garfos;               //arranjo de garfos
  private FrameAnimacao frameAnimacao;  //painel da simulacaoal;
  private SliderFrame sliderFrame;      //painel onde tem os sliders

  /* *********************
  * Metodo: MainFrame
  * Funcao: Construtor
  * Parametros: nenhum
  ********************* */
  public MainFrame(){
    inicializar();
    acaoSliders();

    this.setLayout(null);
    this.add(sliderFrame);
    this.add(frameAnimacao);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(910, 640));
    this.setFocusable(false);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setVisible(true);
    this.setTitle("Jantar dos Monges");

    frameAnimacao.renderizar();
  }

  /* *********************
  * Metodo: inicializar
  * Funcao: inicializa os atributos da classe
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  private void inicializar(){
    display = new Display();

    controle = new Controlador();

    garfos = new Garfo[5];
    for (int i=0; i<5; i++)
      garfos[i] = new Garfo();
    
    pensadores = new Pensador[5];
    
    //passa respectivamente o garfo da esquerda e depois o da direita para o construtor de Pensador
    pensadores[0] = new Pensador(garfos[0], garfos[1], controle, 1);
    pensadores[1] = new Pensador(garfos[1], garfos[2], controle, 2);
    pensadores[2] = new Pensador(garfos[2], garfos[3], controle, 3);
    pensadores[3] = new Pensador(garfos[3], garfos[4], controle, 4);
    pensadores[4] = new Pensador(garfos[4], garfos[0], controle, 5);

    frameAnimacao = new FrameAnimacao(display, pensadores, garfos);
    frameAnimacao.setBounds(0, 0, 900, 450);
    sliderFrame = new SliderFrame();
    sliderFrame.setBounds(0, 450, 900, 150);
  }

  /* *********************
  * Metodo: acaoSliders
  * Funcao: adiciona listeners de acao aos sliders
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  private void acaoSliders(){
    //sliders de tempo de pensamento
    sliderFrame.getSliders()[0].addChangeListener(e -> {
      pensadores[0].setVelPensar(sliderFrame.getSliders()[0].getValue());
    });
    sliderFrame.getSliders()[1].addChangeListener(e -> {
      pensadores[1].setVelPensar(sliderFrame.getSliders()[1].getValue());
    });
    sliderFrame.getSliders()[2].addChangeListener(e -> {
      pensadores[2].setVelPensar(sliderFrame.getSliders()[2].getValue());
    });
    sliderFrame.getSliders()[3].addChangeListener(e -> {
      pensadores[3].setVelPensar(sliderFrame.getSliders()[3].getValue());
    });
    sliderFrame.getSliders()[4].addChangeListener(e -> {
      pensadores[4].setVelPensar(sliderFrame.getSliders()[4].getValue());
    });

    //sliders de tempo para comer
    sliderFrame.getSliders()[5].addChangeListener(e -> {
      pensadores[0].setVelComer(sliderFrame.getSliders()[5].getValue());
    });
    sliderFrame.getSliders()[6].addChangeListener(e -> {
      pensadores[1].setVelComer(sliderFrame.getSliders()[6].getValue());
    });
    sliderFrame.getSliders()[7].addChangeListener(e -> {
      pensadores[2].setVelComer(sliderFrame.getSliders()[7].getValue());
    });
    sliderFrame.getSliders()[8].addChangeListener(e -> {
      pensadores[3].setVelComer(sliderFrame.getSliders()[8].getValue());
    });
    sliderFrame.getSliders()[9].addChangeListener(e -> {
      pensadores[4].setVelComer(sliderFrame.getSliders()[9].getValue());
    });
  }
}
