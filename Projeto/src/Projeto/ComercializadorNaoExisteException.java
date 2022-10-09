package Projeto;

public class ComercializadorNaoExisteException extends  Exception{
    public ComercializadorNaoExisteException(){
        super();
    }

    public ComercializadorNaoExisteException(String s){
        super(s);
    }
}
