package ex_10;
public class ClassesAninhadas {

    public void mensagem(){
        System.out.println("No topo da classe aninhada");
    }

    class ClasseInterna {

        public void naClasseInterna() {
            System.out.println("Dentro da classe interna");
        }
    }

    static class ClassStatic{
        public void naClasseEstatica() {
            System.out.println("Dentro da classe estática");
        }
    }

     void metodoComClasse() {
         class ClasseDentroDoMetodo {
            public void classeNoMetodo() {
                System.out.println("Dentro da classe inserida em um método");
            }
        }
        ClasseDentroDoMetodo clDentro = new ClasseDentroDoMetodo();
         clDentro.classeNoMetodo();
    }

     class ClasseAnonima {
        ClassesAninhadas cAninhada = new ClassesAninhadas() {
                public void mensagem(){
                    System.out.println("Dentro da classe anônima");
                }
        };

         public ClassesAninhadas getcAninhada() {
             return cAninhada;
         }
     }
        public static void main(String[] args){

        ClassesAninhadas clAninhada=new ClassesAninhadas();
        clAninhada.mensagem();
        ClasseInterna clInterna=clAninhada.new ClasseInterna();
        clInterna.naClasseInterna();
        new ClassStatic().naClasseEstatica();
        clAninhada.metodoComClasse();
        ClasseAnonima clAnonima = clAninhada.new ClasseAnonima();
        clAnonima.getcAninhada().mensagem();
        }
    }