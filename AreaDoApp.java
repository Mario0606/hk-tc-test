package sidemenu;

//imports necessários
import java.util.ArrayList;

import totalcross.io.IOException;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.MenuBarDropDown;
import totalcross.ui.MenuItem;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class AreaDoApp extends Container{
	private Bar bar1; //barra superior
	private Container contest,contest2,contest3,contest4,contest5,contest6;  	//dados estáticos
	private Container contestx; 					//Container utilizado no método de criar Containers
	private ArrayList<Container> counts;   								//Array de containers para usar na criação do container master
	private ScrollContainer sc; 											//container principal que une os outros
	private MenuBarDropDown mb; 												//menu aberto ao clicar em um botão
	private MenuItem[] mi = {new MenuItem(""),new MenuItem("Group Chat"),new MenuItem("Add Partner"),new MenuItem("Scan QR Code")}; //itens do menu citado acima
	private Container c,xis; 													//barra inferior e container auxiliares para bordas respectivamente
	private Button cb1,cb2,cb3,cb4; 									// botões da barra inferior
	private ScrollContainer conversaX; 											//Container do chat
	private Label outro,voce;  														// mensagens de quem envia e da outra pessoa
	private Bar barConv;														//bar superior do chat
	private Edit write;														//barra de escrever do chat
	private Button enviar;															//enviar o que escreveu
	private final int COMPONENT_H = fmH * 2; 							//variável que auxilia no programa
	private Container bordas,bordas2,bordas3;						//containers auxiliares(adicionar bordas)
	private ImageControl imgProf;									//imagem do profile de quem enviar
	
	
	
	// método auxiliar na adição de imagens ao programa
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*size), true));
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setBackColor(Color.getRGB(255, 255, 255));
		return image;
	}

	//Criar Containers padrões de chat
	public Container criarContainer(String Nome,String ultimaMsg, String imagem,String hora ) {
		contestx = new Container() {
			private Button nome,ultimasMsgs;
			public void initUI() {
				ImageControl image;
				nome  = new Button(Nome);
				nome.transparentBackground = true;
				ultimasMsgs = new Button(ultimaMsg);
				ultimasMsgs.transparentBackground = true;
				ultimasMsgs.setFont(Font.getFont(false, Font.NORMAL_SIZE-2));
				ultimasMsgs.setForeColor(Color.getRGB(120, 120, 120));
				Label horaDaMsg = new Label(hora);
				horaDaMsg.setFont(Font.getFont(Font.NORMAL_SIZE-4));
				horaDaMsg.setForeColor(Color.getRGB(120, 120, 120));
				image = passarIC(imagem,3);
				add(image,LEFT+10,CENTER, PREFERRED,PREFERRED);
				add(nome,LEFT+70,TOP+(SCREENSIZE+40), PREFERRED,PREFERRED);
				add(ultimasMsgs,SAME+10,AFTER, PREFERRED,PREFERRED);
				add(horaDaMsg,LEFT+(SCREENSIZE+90),AFTER, PREFERRED,PREFERRED);
					
			}
			//abrir o chat caso seja clicado no nome ou na última mensagem recebida e sumir com a tela anterior(deixando apenas o chat)
			public void onEvent(Event event) {
				switch(event.type) {
				case ControlEvent.PRESSED:
					if(event.target == nome || event.target == ultimasMsgs) {
						System.out.println("nao");
						sc.setVisible(false);
						bar1.setVisible(false);
						c.setVisible(false);
						outro.setText(ultimaMsg);
						barConv.setTitle(nome.getText());
						conversaX.setVisible(true);
						barConv.setVisible(true);
						write.setVisible(true);
						enviar.setVisible(true);
						System.out.println(ultimasMsgs.getText().length());
						if(ultimasMsgs.getText().length() <=9)
							xis.setRect(LEFT,TOP+10,ultimasMsgs.getText().length()*30,COMPONENT_H+3);
						else
							xis.setRect(LEFT,TOP+10,ultimasMsgs.getText().length()*12,COMPONENT_H+3);
						imgProf.setImage(passarIC(imagem,1).getImage());
						imgProf.setRect(LEFT,TOP,PREFERRED,FILL);
						outro.setRect(LEFT+30,TOP,FILL,COMPONENT_H);

					}
				}
			}
		};
		contestx.setBorderStyle(BORDER_RAISED);
		return contestx;
	}	
	
	public void initUI() {
		//adicionar controles do chat e deixar ocultos
		setBackColor(Color.WHITE);
		imgProf = new ImageControl(passarIC("retrato1.jpg",1).getImage());
		barConv = new Bar("");
		barConv.addButton(passarIC("seta.png",1).getImage(), false);
		barConv.setVisible(false);
		add(barConv,LEFT,TOP,PREFERRED,PREFERRED);
		counts = new ArrayList<Container>();
		conversaX = new ScrollContainer(){
			public void initUI() {
				setBackColor(Color.getRGB(245, 245, 245));
				xis = new Container() {
					public void initUI(){
						outro = new Label("",CENTER);
						add(imgProf,LEFT,TOP,PREFERRED,FILL);
						add(outro,AFTER,TOP+10,FILL,COMPONENT_H);
					}
				};
				xis.setBorderStyle(BORDER_ROUNDED);
				xis.setBackColor(Color.WHITE);
				add(xis,LEFT+10,TOP+10,PREFERRED,COMPONENT_H+1);
				Label v=new Label();
				add(v,RIGHT,AFTER+10,SCREENSIZE+10,SCREENSIZE+10);
			}
		};
		conversaX.setVisible(false);
		conversaX.setBackColor(Color.WHITE);
		conversaX.setBorderStyle(BORDER_SIMPLE);
		add(conversaX,LEFT,AFTER,FILL,FILL-40);
		System.out.println("xd");
		write = new Edit();
		write.caption = "Type a message";
		write.setVisible(false);
		write.setFont(Font.getFont(Font.NORMAL_SIZE-2));
		write.setForeColor(Color.getRGB(150,150,150));
		add(write,LEFT,AFTER,FILL-50,COMPONENT_H);
		enviar = new Button(passarIC("enviar.png",2).getImage());
		enviar.transparentBackground = true;
		enviar.setVisible(false);
		add(enviar,RIGHT,SAME,PREFERRED,PREFERRED);
		
		
		//criar containers de pessoas no batepapo
		setNextTransitionEffect(TRANSITION_FADE );
		contest = criarContainer("Mariozim06", "eae caraaaaaaaaaaaa", "retrato1.jpg", "15:00 PM");
		contest2 = criarContainer("Levi Rei Dellas","Boa noite ","retrato2.jpg","8:00 AM");
		contest3 = criarContainer("Iagod","Hello My friend","retrato3.jpg","8:00 AM");
		contest4 = criarContainer("Denis","eaee","retrato2.jpg","Yesterday");
		contest5 = criarContainer("Matheus","Show","retrato1.jpg","Yesterday");
		contest6 = criarContainer("Neto","VQV","retrato2.jpg","Yesterday");
		counts.add(contest);
		counts.add(contest2);
		counts.add(contest3);
		counts.add(contest4);
		counts.add(contest5);
		counts.add(contest6);
		
		//adicionar containers a tela de bate papo
		sc = new ScrollContainer() {
			public void initUI() {
				for(int i = 0;i<counts.size();i++) {
					if(i == 0)
						add((Control) counts.get(i),LEFT,TOP,FILL,SCREENSIZE +15);
					else
						add((Control) counts.get(i),LEFT,AFTER,FILL,SCREENSIZE +15);
				}
			}
		};
		
		//barra inferior
		c = new Container() {
			public void initUI() {
				Image azul = passarIC("nuvems.jpg",2).getImage();
				azul.applyColor(Color.getRGB(56,176,222));
				Image comp = passarIC("conexao.png",2).getImage();
				comp.applyColor2(Color.getRGB(205,205,205));
				comp.applyColor(Color.getRGB(205,205,205));
				cb1 = new Button("Talks",azul,BOTTOM,0);
				cb2 = new Button("Moments",comp,BOTTOM,0);
				cb3 = new Button("Search",passarIC("lupa.png",2).getImage(),BOTTOM,0);
				cb4 = new Button("Profile",passarIC("profileX.png",2).getImage(),BOTTOM,0);
				cb1.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb1.setForeColor(Color.getRGB(56,176,222));
				cb2.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb3.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb4.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb1.transparentBackground = true;
				cb2.transparentBackground = true;
				cb3.transparentBackground = true;
				cb4.transparentBackground = true;
				add(cb1,LEFT,TOP,SCREENSIZE+25,FILL);
				add(cb2,AFTER,TOP,SCREENSIZE+25,FILL);
				add(cb3,AFTER,TOP,SCREENSIZE+25,FILL);
				add(cb4,AFTER,TOP,SCREENSIZE+25,FILL);
				
			}
		
		};		
		sc.setBorderStyle(BORDER_LOWERED);
		add(bar1 = new Bar("Language Talks"),LEFT,TOP,FILL,PREFERRED);
		add(sc,LEFT,AFTER,FILL,FILL-50);
		mb= new MenuBarDropDown(RIGHT,TOP+20,mi);
		bar1.addButton(passarIC("curtir.gif",1).getImage());
		add(c,LEFT,AFTER,FILL,FILL);
	}
	
	
	// método utilizado para tratar de eventos
	public void onEvent(Event event) {
		switch (event.type)
	    {
		case ControlEvent.PRESSED:
			// mudar telas
			if(event.target ==cb1) {
				MainWindow.getMainWindow().swap(new AreaDoApp());
			}
			if(event.target ==cb2) {
				MainWindow.getMainWindow().swap(new Moments());
			}
			if(event.target ==cb3) {
				MainWindow.getMainWindow().swap(new Search());
			}
			if(event.target ==cb4) {
				MainWindow.getMainWindow().swap(new Profile());
			}
			//aparecer menu ao clicar no botão
			else if(event.target ==bar1 && bar1.getSelectedIndex() == 1) {
				mb.popupNonBlocking ();
			}
			//adicionar texto a uma label no container da conversa
			else if(event.target == enviar) {
				bordas = new Container() {
					public void initUI() {
						voce =new Label(write.getText(),CENTER);
						add(voce,LEFT,TOP,FILL,FILL);
					}
				};
				bordas.setBackForeColors(Color.getRGB(173,234,234), Color.BLACK);
				bordas.setBorderStyle(BORDER_ROUNDED);
				conversaX.add(bordas, RIGHT-10, AFTER+5, write.getText().length()*12, COMPONENT_H+2);
				
				//resposta do bot
				if(write.getText().equals("tudo bom?") || write.equals("como está?")) {
					bordas2 = new Container() {
						public void initUI() {
							voce =new Label("Tudo sim velho.",CENTER);
							add(imgProf,LEFT,AFTER,PREFERRED,FILL);
							add(voce,LEFT+30,TOP,FILL,COMPONENT_H);
						}
					};
					bordas2.setBackForeColors(Color.WHITE, Color.BLACK);
					bordas2.setBorderStyle(BORDER_ROUNDED);
					conversaX.add(bordas2, LEFT, AFTER+5, write.getText().length()*16, COMPONENT_H+3);
				}
				else {
					String x = "Repete, nao entendi.";
					bordas3 = new Container() {
						public void initUI() {
							voce =new Label("Repete,nao entendi",CENTER);
							add(imgProf,LEFT,AFTER,PREFERRED,FILL);
							add(voce,LEFT+30,TOP,FILL,COMPONENT_H);
						}
					};
					bordas3.setBackForeColors(Color.WHITE, Color.BLACK);
					bordas3.setBorderStyle(BORDER_ROUNDED);
					conversaX.add(bordas3, LEFT, AFTER+5, x.length()*9, COMPONENT_H+3);
				}
				write.setText("");
	
					
			}
			//fechar chat e voltar a tela de possiveis conversas
			else if(event.target == barConv && barConv.getSelectedIndex() == 1) {
				sc.setVisible(true);
				bar1.setVisible(true);
				c.setVisible(true);
				conversaX.setVisible(false);
				barConv.setVisible(false);
				write.setVisible(false);
				enviar.setVisible(false);
				conversaX.remove(bordas);
			}
			}
		
	    }
    
}

