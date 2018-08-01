Login: mario
Senha:123

Introdução:
	O aplicativo foi feito de forma estática, assim, algumas funcionalidades não foram implementadas por causa do curto período. Por ser um teste, optei por implementar “espécies” de funções diferentes, assim, funções como “abrir uma nova tela”, em muitos casos não foram implementadas, o resumo será dividido em telas para um melhor entendimento do aplicativo.

Tela Inicial
	Essa tela basicamente possui dois botões que levam a diferentes telas. Botão 1 - SignUp -  abre uma subtela que permite o usuário escolher a rede social que auxiliará para o login( nessa escolha optei por deixar que já entrasse direto(já que não possui contato com o servidor). Botão 2 - Login - Enviado a uma tela de Login.
Curiosidades de implementação: na subtela de signUP é um container criado previamente e setado para ser visto assim que é clicado no botão.
	
Tela de Login
	Tela básica de login com email e password, onde possui também botão de “esqueci meu password” e um checklist que seta se o password será salvo para futuramente ou não. Possui também o botão de SignUp que mais uma vez levará à subtela e o botão de voltar. Botão de login levará, em caso de dados corretos, ao aplicativo em si.
	Curiosidades de implementação: é feito uma lógica para mudar a cor do check e caso login ou senha estejam errados surgirá uma MessageBox informando isso. SignUp possui lógica similar a usada antes. Existe um “X” que apaga o que foi escrito nos Edits

Tela de Forget Password
	Tela em que será informado o email e clicado para enviar um link para o email. Há também o botão de voltar.
Curiosidade de implementação: em caso de email em branco será informado que deve ser escrito um email, em caso de escrever um email será informado que o link foi enviado para email, todas as duas informações através de MessageBox. Lógica do “X” igual a do login.

Tela de Conversas:
	Tela que mostra as conversas e basicamente é dividida em tem etapas: Barra superior(com nome e link para um submenu), um espaço onde estão todas as conversas do usuário, e a barra inferior que levará a diferentes funcionalidades do aplicativo(barra implementada em todas as quatro telas principais do aplicativo).
  Curiosidades de Implementação: foram feitos containers referentes a cada conversa e implementados em um scrollContainer de forma estática, mas de forma dinâmica a lógica também funcionária, por ter sido usados métodos específicos. em caso de clicar em alguma conversa levará para o chat, o mesmo já foi implementado nessa tela porém estar oculto, isso ,como também outras implementações anteriores, é feito por estar sendo usados dados estáticos, dificultando a passada de informação de uma classe para outra.

Tela de chat
	A tela de chat, terá uma barra superior, onde o título será alterado de acordo com o nome da pessoa que o usuário está falando, terá a parte onde estará o que foi digitado e uma parte inferior onde o usuário pode escrever e enviar.
	Curiosidades de Implementação: Nessa tela a primeira mensagem do profile que está falando com o usuário é uma estática, porém, caso o usuário digite “tudo bom?” ou “como está?” o profile responderá de um jeito, e responderá responderá “Repete, não entendi.” para qualquer outra mensagem enviada.

Tela de moments
  Tela padrão de feed, onde usuários postam suas coisas.Possui os três elementos da tela de talk, porém os espaços reservados para as postagens tendem a serem maiores que o reservado para conversas. Possui diferentes tipos de postagens, e essas são divididas em um menu para que o usuário procure o que mais lhe interessar. Quando executei o app usado como base, este só mostrava o “default” então optei por mostrar o “erro” semelhante ao que vi nos outros itens de menu: “Learn”, “Following” e “Classmates”.
  Curiosidades de implementação: lógica de adicionar containers a um ScrollContainer é a mesma da tela Talk, a diferença é na função criarContainer, já que é criado um novo tipo de container.
  
Tela de Search
	Tela de procura de novos amigos, aparecem os melhores possíveis para o usuário, porém, o mesmo pode escolher o melhor menu de busca, através de um menu na parte superior que pode alterar de “Best Match” para “Online”, “Nearest” ou “Self”, mostrando novos usuários.
  Curiosidades de implementação: É alterado os usuários de acordo com o “click” no menu, a lógica é que cada item do menu possui uma lista e quando escolhida a lista mostrada será a do mesmo. A criarContainer dessa tela é bastante semelhante a da tela de talk pelo fato de os containers serem perfis.
Tela de profile
	A tela de profile mostra as informações do usuário e sua parte principal é dividida em 4: “Informações”, “Atividades”, “Introdução sobre você” e logout, três possuem nomes bastantes similares a suas funções,então será comentado sobre o espaço de atividades, este possui imagens e números, e mostram quantas atividades, como “traduções”, foram feitas pelo usuário.
	Curiosidades de implementação: na parte de atividades quando é clicado nas imagens aparece um MessageBox informando o que foi feito(como no exemplo acima).

	
