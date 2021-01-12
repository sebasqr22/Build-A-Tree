package buildatree;

public class PlayersTrees {

    String arbol;

    Player player1 = new Player();

    Player player2 = new Player();

    Player player3 = new Player();

    Player player4 = new Player();

    public void setArbol(String data){
        arbol = data;
    }

    public void comenzar(int numeroJugadores){
        if (numeroJugadores == 2){
            player1.setArbol(arbol);
            player2.setArbol(arbol);

            if (arbol.equals("AVL")){

            }
            else if (arbol.equals("BST")){

            }
            else if (arbol.equals("SPLAY")){

            }
            else{

            }
        }

        else if (numeroJugadores == 3){
            player1.setArbol(arbol);
            player2.setArbol(arbol);
            player3.setArbol(arbol);
        }

        else{
            player1.setArbol(arbol);
            player2.setArbol(arbol);
            player3.setArbol(arbol);
            player4.setArbol(arbol);
        }
    }
}
