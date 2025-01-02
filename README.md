<p align="center">
    <img src="https://www.emailvendorselection.com/wp-content/uploads/Simple-Mail-Transfer-Protocol.png" width="600px">
</p>

<p align="center">
<b>Bem-vindo ao projeto <code>EmailSender</code>, uma solução Open Source para envio de e-mails com suporte a anexos e logs 🚀📧</b>
</p>

---

## 📜 Descrição

O **EmailSender** é um projeto Open Source desenvolvido para facilitar o envio de e-mails utilizando o protocolo SMTP. Com funcionalidades robustas, o projeto permite o envio em lote, suporte a anexos e registro detalhado de logs.

---

## 🛠️ Funcionalidades

- **Configuração Personalizável de SMTP**: Permite definir host e porta SMTP para se adaptar a diferentes provedores.
- **Envio de E-mails com Anexos**: Suporte a múltiplos anexos com validação de arquivos.
- **Envio em Lote**: Utilize threads para enviar e-mails de forma assíncrona e eficiente.
- **Registro de Logs**: Salve detalhes de cada envio, incluindo status (SUCESSO ou FALHA) e mensagens de erro, em um arquivo de log.
- **Corpo do E-mail em HTML**: Suporte para criar e-mails com conteúdo HTML formatado.
- **Sistema de Autenticação**: Implementação de autenticação com credenciais para envio seguro.
- **Execução Multithread**: Gerenciamento de envio em paralelo usando um pool de threads configurável.

---

## 🚀 Como Contribuir

Se você deseja contribuir, siga os passos abaixo para começar a colaborar neste projeto incrível:

1. Clone este repositório:
    ```bash
    git clone https://github.com/VitorOdorico/smtpServer.git
    ```

2. Crie sua branch de trabalho:
    ```bash
    git checkout -b feature/NOME_DA_FEATURE
    ```

3. Após finalizar suas alterações, abra um Pull Request explicando as mudanças realizadas. Adicione capturas de tela, se necessário, e aguarde a revisão!

### Recursos Úteis:
- [Como criar um Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)
- [Padrões de Commit](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

---

## 📃 Licença

Este projeto está licenciado sob a [MIT](./LICENSE) License. Sinta-se livre para usá-lo, modificá-lo e redistribuí-lo conforme necessário.

---

## 📷 Capturas de Tela
<div align="center">
  <img src="https://vitorodorico.com/files/emailEnviadosmtp.jpeg" width="500px" />
<img src="https://vitorodorico.com/files/telasmtp.jpeg" width="500px" />
<img src="https://vitorodorico.com/files/logsmtp.jpeg" width="500px" />
</div>
### Exemplo de Log
[2025-01-01 12:34:56] Destinatário: exemplo@dominio.com | Assunto: Teste | Status: SUCESSO <br>
[2025-01-01 12:35:12] Destinatário: exemplo2@dominio.com | Assunto: Teste | Status: FALHA: Mensagem de erro




---

Sinta-se à vontade para explorar, contribuir e usar o **EmailSender** no seu próximo projeto!
