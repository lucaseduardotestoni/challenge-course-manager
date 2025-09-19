#  Desafio de Código: Pessoa Desenvolvedora Java

Bem-vindo ao teste para Pessoa Desenvolvedora Java!  
Este repositório contém o desafio técnico que simula parte do domínio de uma plataforma educacional.  
O objetivo é avaliar a aplicação de conceitos de **lógica**, **orientação a objetos** e **boas práticas de desenvolvimento** para resolver problemas.

---

##  Sobre o Desafio
Neste projeto, você implementará funcionalidades essenciais de uma plataforma de cursos, como **cadastro, inativação e matrícula**, além da **geração de relatórios**.  
O foco está na **lógica de back-end** e na construção de uma **API robusta e eficiente**.


---

## Tecnologias Utilizadas
O projeto está configurado com a seguinte stack tecnológica:

- **Java**: 18 ou superior  
- **Framework**: Spring Boot  
- **Persistência**: Spring Data JPA  
- **Banco de Dados**: MySQL  
- **Migrações**: Flyway (migrações manuais)  

---

###  Questão 1: Cadastro de Cursos

**Atributos do Curso**
- `name` → Nome  
- `code` → Código (entre 4 e 10 caracteres)  
- `instructor` → Referência a um `User`  
- `description` → Descrição  
- `status` → Enum (`ACTIVE` ou `INACTIVE`)  
- `creation_date` → Data de Criação  
- `inactivation_date` → Data de Inativação  

**Regras de Negócio**
- O código (`code`) deve ser **único**, textual, sem espaços, sem números e sem caracteres especiais (exceto `-`).  
  Exemplo: `spring-boot-avancado`.  
- Apenas usuários com a role **INSTRUCTOR** podem ser cadastrados como instrutores.  
- Novos cursos devem ser criados com **status = ACTIVE** por padrão.  
- O campo `inactivation_date` só deve ser preenchido quando o curso for **inativado**.  

---

###  Questão 2: Inativação de Cursos

**Regras de Negócio**
- A inativação deve ser feita através da rota **PATCH /course/{code}/inactive**.  
- Ao inativar:
  - O `status` deve mudar para **INACTIVE**.  
  - A data/hora atual deve ser registrada em `inactivation_date`.  

---

###  Questão 3: Matrícula de Alunos

**Atributos da Matrícula**
- `user` → Aluno que está se matriculando  
- `course` → Curso em que o aluno está se matriculando  
- `enrollment_date` → Data da matrícula  

**Regras de Negócio**
- Um usuário não pode se matricular mais de uma vez no mesmo curso.  
- Matrículas só são permitidas em cursos com **status ACTIVE**.  

---

###  Questão 4: Relatório de Cursos com Mais Matrículas

**Requisitos**
- Implemente a lógica na rota **GET /registration/report**.  
- O resultado deve ser uma lista de cursos, ordenada de forma **decrescente** pelo número de matrículas.  

 **Importante**:  
A plataforma possui um grande volume de dados.  
Para garantir performance:
- Priorize o uso de **SQL nativo**.  
- Evite o anti-pattern **N+1**.  

---

##  Critérios de Avaliação

- **Implementação dos Requisitos**: Regras de negócio aplicadas corretamente.  
- **Qualidade do Código**: Clareza, organização, aplicação de **OOP** e boas práticas (**SOLID**).  
- **Histórico de Commits**: Commits atômicos e mensagens claras.  
  > Seguir **Conventional Commits** é um diferencial.  
- **Tecnologias Extras**: Tecnologias fora do escopo (**Swagger, Docker, etc.**) não serão consideradas na avaliação.  
- **Dúvidas**: Caso tenha dúvidas, registre no código e implemente a solução que julgar mais adequada.  
- **Autenticidade**: Códigos muito semelhantes aos de outros candidatos podem resultar em **anulação** do teste.  
- **Uso de IA**: É permitido, mas você deve ser capaz de **explicar e justificar** todo o código durante a entrevista técnica.  

---
