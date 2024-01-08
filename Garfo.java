import java.util.concurrent.Semaphore;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 30 de Maio de 2021
Alteracao: 04 de Junho de 2021
Nome.....: Garfo
Funcao...: Classe que cria objeto de garfo que possui um semaforo local
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Garfo {
  private Semaphore semaforoLocal; //semaforo local
  private Sprite imagem;           //sprite do garfo
  
  /* *********************
  * Metodo: Garfo
  * Funcao: Construtor
  * Parametros: nenhum
  ********************* */
  public Garfo(){
    semaforoLocal = new Semaphore(1, true);
    imagem = new Sprite("res/garfo.png");
  }

  /* *********************
  * Metodo: up
  * Funcao: usa o metodo acquire do semaforo
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void up(){
    try {
      semaforoLocal.acquire();
    } 
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /* *********************
  * Metodo: down
  * Funcao: usa o metodo release do semaforo
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void down(){
    semaforoLocal.release();
  }

  /* *********************
  * Metodo: estaDisponivel
  * Funcao: retorna verdadeiro se o semaforo estiver disponivel e falso se nao estiver
  * Parametros: nenhum
  * Retorno: boolean
  ********************* */
  public boolean estaDisponivel(){
    return (semaforoLocal.availablePermits()!=0);
  }

  //metodo getter e setter
  public Sprite getImagem() {
    return imagem;
  }

  public void setImagem(Sprite imagem) {
    this.imagem = imagem;
  }
}
