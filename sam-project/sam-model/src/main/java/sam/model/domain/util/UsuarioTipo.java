package sam.model.domain.util;

public enum UsuarioTipo {
   DESENVOLVEDOR,
   CLIENTE,
   GESTOR;

   public static UsuarioTipo strTo(String strTipo) {
      switch (strTipo) {
         case "DESENVOLVEDOR" :
            return DESENVOLVEDOR;
         case "CLIENTE" :
            return CLIENTE;
         case "GESTOR" :
            return GESTOR;
      }

      throw new RuntimeException(strTipo + " não é um tipo válido.");
   }

}