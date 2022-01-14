package ex_04;

 class InicializacaoTardia {


   private final int _FINAL_ATTRIBUTE1_;
   private final static int  _FINAL_ATTRIBUTE2_;

     static {
         _FINAL_ATTRIBUTE2_ = 0;
     }
    public InicializacaoTardia(int _FINAL_ATTRIBUTE1_) {
        this._FINAL_ATTRIBUTE1_ = _FINAL_ATTRIBUTE1_;
    }

    public static void main(String[] args) {
        InicializacaoTardia it = new InicializacaoTardia(1);
        System.out.printf("Atributo 1: %d\nAtributo 2: %d",it._FINAL_ATTRIBUTE1_,_FINAL_ATTRIBUTE2_);
    }
}
