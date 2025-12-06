package sam.controller;

import sam.model.service.DenunciaService;

public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController() {
        this.denunciaService = new DenunciaService();
    }


    public void registrarDenuncia(Integer denuncianteIdInt,
                                  Integer denunciadoIdInt,
                                  String motivo,
                                  String detalhes) {

        try {
            if (denuncianteIdInt == null || denunciadoIdInt == null) {
                System.out.println("IDs de usuário inválidos.");
                return;
            }

          
            Long denuncianteId = denuncianteIdInt.longValue();
            Long denunciadoId = denunciadoIdInt.longValue();

            denunciaService.registrarDenuncia(denuncianteId, denunciadoId, motivo, detalhes);

            System.out.println("Denúncia registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar denúncia: " + e.getMessage());
        }
    }
}
