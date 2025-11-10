# Sistema de Administração de Milhas
Esse software tem a missão de automatizar e facilitar a análise de transações e gestão das milhas dos clientes, ou seja, visa atender os gestores e analistas no mercado de milhas.

## Equipe de Desenvolvimento
| Ordem | Nome               |
|:------|:-------------------|
| 1     | Francisco          |
| 2     | Gabriel Marrocos   |
| 3     | Mateus Tertuliano  |
| 4     | Mateus Vasconcelos |
| 5     | Thiérs             |
| 6     | Verônica           |

## Atores do Sistema
| Ator     | Definição                                                                                         |
|:---------|:--------------------------------------------------------------------------------------------------|
| Gestor   | Usuário com cargo de Gestor, utilizará o sistema para gerenciar milhas e transações dos clientes. |
| Cliente  | Usuário com cargo de Cliente, utilizará o sistema para acessar sua conta para acompanhamento.     |
| Dev      | Usuário que representa o Desenvolvedor, utilizará o sistema para gerenciar os gestores.           |

## Requisitos Funcionais
| Id     | Ator              | Descrição |
|:-------|:------------------|:-----------|
| REQ001 | Cliente / Gestor  | Preciso cadastrar minha conta e dados pessoais. Após o cadastro, os dados poderão ser atualizados. |
| REQ002 | Gestor            | Preciso gerenciar empresas e programas de fidelidade. Após o cadastro, os dados poderão ser atualizados. |
| REQ003 | Gestor            | Preciso associar empresas e programas de fidelidade aos clientes. |
| REQ004 | Gestor            | Preciso adicionar/remover clientes como meus, informando o plano de acesso ao adicionar. O plano de acesso pode ser atualizado. |
| REQ005 | Cliente / Gestor  | Preciso registrar as transações de compra e venda, separadas por conta de cliente. |
| REQ006 | Cliente / Gestor  | Preciso visualizar transações realizadas e os saldos em um dashboard. Gestores têm acesso a esses dados referentes a todos os seus clientes de forma separada. |
| REQ007 | Gestor            | Preciso que o sistema compare e calcule se a compra de uma determinada quantidade de milheiro vale a pena de acordo com o valor prefixado do milheiro de segurança. |
| REQ008 | Gestor            | Preciso ter acesso a datas de expiração de milhas e programas de fidelidade, recebendo avisos e notificações sobre tais expirações. |
| REQ009 | Gestor            | Preciso criar e visualizar avisos e notificações para os clientes. |
| REQ010 | Gestor            | Preciso comparar valores que serão pagos usando milhas, dinheiro ou milhas e dinheiro. |
| REQ011 | Cliente           | Preciso fazer a solicitação para uma conta de gestor. A solicitação pode ser removida. |
| REQ012 | Gestor            | Preciso configurar o milheiro de segurança por programa de fidelidade, sendo atualizado manualmente. |
| REQ013 | Cliente / Gestor  | Preciso avaliar/denunciar um usuário, podendo adicionar/remover comentários e atualizá-los depois. |
| REQ014 | Cliente           | Preciso aprovar ou recusar propostas/orçamentos e visualizá-los. |
| REQ015 | Cliente           | Preciso ter acesso a um questionário de definição de perfil e objetivos gerais/específicos, podendo ser atualizado. No questionário, é possível inserir dados sobre viagens (número de pessoas, destino, companhias aéreas, etc.) para que orçamentos possam ser gerados. |
| REQ016 | Cliente           | Preciso comunicar ao sistema em caso de cancelamento de proposta já aprovada. |
| REQ017 | Cliente           | Preciso receber mensagens, notificações e avisos do gestor. |
| REQ018 | Dev               | Preciso habilitar/desabilitar funcionalidades para cada conta, podendo aplicar punições quando necessário. |
| REQ019 | Dev               | Preciso receber as solicitações para conta de Gestor. |
| REQ020 | Gestor            | Preciso criar, remover e atualizar as propostas/orçamentos que serão enviadas para cada cliente. |
| REQ021 | Cliente / Gestor  | Preciso ter acesso ao histórico de outros usuários contendo as avaliações, empresas/programas de fidelidade associados, desempenho com clientes/gestores que já trabalharam, etc. |
| REQ022 | Cliente / Gestor  | Preciso recuperar meus dados (senhas, e-mail de login, etc.) quando necessário. |
| REQ023 | Gestor            | Preciso configurar planos de assinatura padrões/específicos, ditando as funcionalidades que meus clientes podem acessar. |
| REQ024 | Dev               | Preciso ter acesso às atividades realizadas no sistema para fins de segurança. |
| REQ025 | Gestor            | Preciso importar dados externos quando necessário. |
| REQ026 | Gestor            | Preciso comparar milhas em diferentes programas de fidelidade para gerar estratégias de negócio. |
| REQ027 | Gestor            | Preciso criar campanhas promocionais que sejam enviadas para todos os meus clientes dentro do público-alvo definido. |
| REQ028 | Cliente / Gestor  | Preciso ter a opção de bloquear usuários, retirando o meu acesso a funcionalidades relacionadas a ele e vice-versa. |
| REQ029 | Cliente / Gestor  | Preciso ter acesso a um sistema interno de tickets de suporte e FAQ para dúvidas e problemas, podendo adicionar, remover e editar perguntas e respostas. |
| REQ030 | Dev               | Preciso visualizar os tickets de suporte, respondê-los e configurar o FAQ, podendo atualizar e excluir perguntas e respostas. |

## Regras de Negócio

| Id     | Nome                                               | Descrição |
|:-------|:---------------------------------------------------|:-----------|
| RN001  | Somente um CPF por usuário                         | Entre os usuários não poderá ser aceito mais de um CPF. |
| RN002  | Somente uma conta por usuário                      | O mesmo usuário não poderá ter duas contas. |
| RN003  | Cancelamento de propostas aprovadas                | O cliente deverá comunicar ao sistema que cancelou uma proposta já aprovada para removê-la do seu histórico. |
| RN004  | Registro de bônus nas transações                   | Toda aquisição bonificada deve ser identificada no registro. |
| RN005  | Programa de fidelidade vinculado à empresa         | Um programa de fidelidade só pode ser vinculado a um cliente se a empresa associada já estiver cadastrada no sistema. |
| RN006  | Notificações obrigatórias para eventos críticos    | O sistema deve sempre gerar notificações automáticas para gestores e clientes sobre eventos como: expiração de milhas, encerramento de programa de fidelidade e oportunidades de compra vantajosas. |
| RN007  | Plano de acesso define funcionalidades disponíveis | O acesso às funcionalidades do sistema será controlado de acordo com o plano do cliente. |
| RN008  | Propostas devem ser aprovadas pelo cliente         | Uma proposta só poderá ser executada após aprovação expressa do cliente. |
| RN009  | Ações do gestor devem ser autorizadas pelo cliente | Um gestor só pode cadastrar um cliente como seu se houver autorização do próprio. |
| RN010  | Notificações por e-mail e plataforma               | Todas as notificações são enviadas tanto por e-mail quanto dentro da plataforma. |
| RN011  | Dados de usuário                                   | Todas as informações de transações, clientes e empresas vinculadas ao usuário devem permanecer salvas enquanto sua conta estiver ativa. |
| RN012  | Verificação de denúncias                           | Denúncias devem ser notificadas e verificadas pelo Dev. A verificação é feita via comunicação por e-mail. |
| RN013  | Verificação de dados de transações                 | Transações só poderão ser adicionadas com a devida nota fiscal vinculada. |
| RN014  | Cálculo dos orçamentos/propostas                   | Os cálculos de orçamento/propostas devem ser feitos levando em conta os objetivos definidos pelo cliente. |
| RN015  | Recuperação de dados de acesso                     | A recuperação só poderá ser realizada com a devida verificação de identidade por e-mail ou telefone. |
| RN016  | Abertura de ticket com autenticação                | Apenas usuários autenticados podem abrir tickets de suporte; os não autenticados possuem acesso apenas ao FAQ. |


## Casos de Uso
| Id     | Nome                                       | Requisitos Relacionados                       | Regras de Negócio Relacionadas |
|:-------|:-------------------------------------------|:----------------------------------------------|:-------------------------------|
| CSU01  | Gestão de perfil                           | REQ001                                        | RN001, RN002                   |
| CSU02  | Gestão de empresas/programas de fidelidade | REQ002, REQ003, REQ012                        | RN005                          |
| CSU03  | Atendimento de cliente                     | REQ004                                        | RN007, RN009                   |
| CSU04  | Gestão de transações                       | REQ005, REQ006                                | RN004, RN007, RN011, RN013     |
| CSU05  | Computar milhas                            | REQ007, REQ010, REQ026                        | RN008, RN011                   |
| CSU06  | Gestão de notificações                     | REQ008, REQ009, REQ017                        | RN006, RN010                   |
| CSU07  | Solicitar conta de gestor                  | REQ011, REQ019                                | RN001                          |
| CSU08  | Gestão de avaliações                       | REQ013                                        | RN012                          |
| CSU09  | Gestão de propostas/orçamentos             | REQ014, REQ016, REQ020, REQ027                | RN003, RN008, RN014            |
| CSU10  | Gestão de objetivos                        | REQ015                                        | RN014                          |
| CSU11  | Gestão de usuários                         | REQ018                                        | RN001                          |
| CSU12  | Recuperação de dados                       | REQ022                                        | RN015                          |
| CSU13  | Visualizar relatórios de perfil            | REQ021                                        | —                              |
| CSU14  | Configuração de planos                     | REQ023                                        | —                              |
| CSU15  | Monitoramento de atividades                | REQ024                                        | RN011                          |
| CSU16  | Importação de dados                        | REQ025                                        | —                              |
| CSU17  | Bloqueio de usuário                        | REQ028                                        | —                              |
| CSU18  | Central de ajuda e suporte                 | REQ029, REQ030                                | RN016                          |

## Planejamento
| Sprint | Caso de Uso | Desenvolvedor  |
|:-------|:------------|:---------------|
| 1      | CSU10       | 1              |
| 1      | CSU14       | 2              |
| 1      | CSU02       | 3              |
| 1      | CSU01       | 4              |
| 1      | CSU05       | 5              |
| 1      | CSU11       | 6              |
| 2      | CSU09       | 1              |
| 2      | CSU03       | 2              |
| 2      | CSU06       | 3              |
| 2      | CSU04       | 4              |
| 2      | CSU08       | 5              |
| 2      | CSU07       | 6              |
| 3      | CSU17       | 1              |
| 3      | CSU12       | 2              |
| 3      | CSU15       | 3              |
| 3      | CSU16       | 4              |
| 3      | CSU13       | 5              |
| 3      | CSU18       | 6              |
