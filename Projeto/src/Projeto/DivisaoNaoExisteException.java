package Projeto;

public class DivisaoNaoExisteException extends Exception{
    public DivisaoNaoExisteException(){
        super();
    }

    public DivisaoNaoExisteException(String s){
        super(s);
    }
}
