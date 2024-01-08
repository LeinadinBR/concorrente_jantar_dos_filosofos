import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 05 de Junho de 2021
Alteracao: 05 de Junho de 2021
Nome.....: SliderFrame
Funcao...: Classe que tem os slider de controle de velocidade. Herda de JPanel
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class SliderFrame extends JPanel {
  private JSlider[] sliders;    //arranjo com os sliders
  private JLabel[] labels;      //arranjo com os labels
  private Color[] cores;        //arranjo com as cores

  /* *********************
  * Metodo: SliderFrame
  * Funcao: Construtor
  * Parametros: nenhum
  ********************* */
  public SliderFrame(){
    inicializar();

    this.setSize(new Dimension(900, 600));
    this.setLayout(new GridLayout(2, 10, 5, 5));

    int count = 0;
    //adiciona os sliders e labels ao painel
    for (int i=0; i<20; i++){
      if (i%2==0){
        this.add(labels[i-count]);
        count++;
      }
      else {
        this.add(sliders[i-count]);
      }
    }
  }

  /* *********************
  * Metodo: inicializar
  * Funcao: inicializa os atributos da classe
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  private void inicializar(){
    cores = new Color[5];
    cores[0] = new Color(255, 160, 95);
    cores[1] = new Color(255, 232, 120);
    cores[2] = new Color(90, 210, 210);
    cores[3] = new Color(215, 35, 35);
    cores[4] = new Color(25, 225, 50);

    sliders = new JSlider[10];
    labels = new JLabel[10];
    
    int count = 0;
    //inicializa os sliders e labels, e tambem adiciona cores aos sliders
    for (int i=0; i<10; i++){
      sliders[i] = new JSlider(0,10,5);
      if (i<5){
        labels[i] = new JLabel("Meditar " + (i+1));
        sliders[i].setBackground(cores[count]);
        count++;
      }
      else {
        if (count>4)
          count=0;
        labels[i] = new JLabel("Comer " + (i-4));
        sliders[i].setBackground(cores[count]);
        count++;
      }
    }
  }

  //metodos getter e setter--------------------
  public JSlider[] getSliders() {
    return sliders;
  }

  public void setSliders(JSlider[] sliders) {
    this.sliders = sliders;
  }
  //-------------------------------------------
}
