import java.util.Random;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 30 de Maio de 2021
Alteracao: 05 de Junho de 2021
Nome.....: Pensador
Funcao...: Classe para representar cada pensador/filosofo. herda de Thread
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Pensador extends Thread {

  private int estado;                         //inteiro que guarda o estado atual do pensador
  private Random randomizadorTempo;           //objeto de Random que gera numeros pseudo-aleatorios
  private Sprite[] imagens;                   //arranjo com as imagens do pensador
  private Garfo garfoEsquerdo, garfoDireito;  //referencia dos garfos adjacentes ao pensador
  private Controlador controle;               //referencia ao controlador que tem o semaforo geral                 
  private int velComer = 5;                   //variavel de tempo para comer
  private int velPensar = 5;                  //variavel de tempo para pensar

  public static final int PENSANDO = 0;       //inteiro da classe indicando o estado de 'pensando'
  public static final int ESPERANDO = 1;      //inteiro da classe indicando o estado de 'esperando'
  public static final int COMENDO = 2;        //inteiro da classe indicando o estado de 'comendo'

  /* *********************
  * Metodo: Pensador
  * Funcao: Construtor
  * Parametros: Garfo garfoEsquerdo, GarfoDireito, Controlador controle, int cor
  ********************* */
  public Pensador(Garfo garfoEsquerdo, Garfo garfoDireito, Controlador controle, int cor){
    estado = Pensador.PENSANDO;
    randomizadorTempo = new Random();

    imagens = new Sprite[3];

    //dependo do valor do int cor, ele escolhe uma imagem
    if (cor == 1){
      imagens[0] = new Sprite("res/mongePensando.png");
      imagens[1] = new Sprite("res/mongeEsperando.png");
      imagens[2] = new Sprite("res/mongeComendo.png");
    }
    else {
      imagens[0] = new Sprite("res/mongePensando"+ String.valueOf(cor) +".png");
      imagens[1] = new Sprite("res/mongeEsperando"+ String.valueOf(cor) +".png");
      imagens[2] = new Sprite("res/mongeComendo"+ String.valueOf(cor) +".png");
    } 
    

    this.garfoEsquerdo = garfoEsquerdo;
    this.garfoDireito = garfoDireito;
    this.controle = controle;
  }

  /* *********************
  * Metodo: run
  * Funcao: algoritmo que a thread segue
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  @Override
  public void run(){
    //roda indefinitivamente
    while (true){
      switch(estado){
        //caso o pensador esteja dormindo
        case 0:
          dormir(velPensar);
          estado = Pensador.ESPERANDO;
          break;
        //caso o pensador esteja esperando para comer
        case 1:
          try {
            controle.getSemaforoGeral().acquire();
            
            if (garfoEsquerdo.estaDisponivel() && garfoDireito.estaDisponivel()){
              garfoDireito.up();
              garfoEsquerdo.up();
              estado = Pensador.COMENDO;
            }            
            controle.getSemaforoGeral().release();
          }
          catch (InterruptedException e) {}
          break;
        //caso o pensador esteja comendo
        case 2:
          dormir(velComer);
          
          garfoEsquerdo.down();
          garfoDireito.down();
          
          estado = Pensador.PENSANDO;
          break;
      }
    }
  }

  /* *********************
  * Metodo: randomizar
  * Funcao: Gera um numero aleatorio 
  * Parametros: nenhum
  * Retorno: int
  * TODO
  ********************* */
  public int randomizar(int tempo){
    int retorno = randomizadorTempo.nextInt(tempo*2);
    if (retorno<tempo) 
      return randomizar(tempo);
    return retorno;
  }

  /* *********************
  * Metodo: dormir
  * Funcao: dorme por um tempo determinado pelo metodo randomizar
  * Parametros: nenhum
  * Retorno: void TODO
  ********************* */
  public void dormir(int velocidade){
    try {
      sleep(randomizar(velocidade)*1000);
    } 
    catch (InterruptedException e) {}
  }

  //getters e setters-----------------------------------
  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public Sprite[] getImagens() {
    return imagens;
  }

  public void setImagens(Sprite[] imagens) {
    this.imagens = imagens;
  }

  public Garfo getGarfoEsquerdo() {
    return garfoEsquerdo;
  }

  public void setGarfoEsquerdo(Garfo garfoEsquerdo) {
    this.garfoEsquerdo = garfoEsquerdo;
  }

  public Garfo getGarfoDireito() {
    return garfoDireito;
  }

  public void setGarfoDireito(Garfo garfoDireito) {
    this.garfoDireito = garfoDireito;
  }

  public int getVelComer() {
    return velComer;
  }

  public void setVelComer(int velComer) {
    this.velComer = velComer;
  }

  public int getVelPensar() {
    return velPensar;
  }

  public void setVelPensar(int velPensar) {
    this.velPensar = velPensar;
  }
  //--------------------------------------------------
}
