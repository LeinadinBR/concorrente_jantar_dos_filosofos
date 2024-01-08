import java.util.concurrent.Semaphore;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 30 de Maio de 2021
Alteracao: 04 de Maio de 2021
Nome.....: Controlador
Funcao...: Classe que possui o semaforo principal do programa
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Controlador {
  private Semaphore semaforoGeral;

  /* *********************
  * Metodo: Controlador
  * Funcao: Construtor
  * Parametros: nenhum
  ********************* */
  public Controlador(){
    semaforoGeral = new Semaphore(1,true);
  }

  //metodos getters e setters-----------------------------
  public Semaphore getSemaforoGeral() {
    return semaforoGeral;
  }

  public void setSemaforoGeral(Semaphore semaforoGeral) {
    this.semaforoGeral = semaforoGeral;
  }
  //------------------------------------------------------
}
