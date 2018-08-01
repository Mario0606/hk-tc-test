package sidemenu;

//imports necessários
import java.util.ArrayList;
import totalcross.io.IOException;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Moments extends Container{
	private Bar bar1;  //barra superior
	private Container contestx; //Container utilizado no método de criar Containers
	private ArrayList<Container> counts;   //Array de containers para usar na criação do container master
	private String [] nomes = {"10","20"};            
	private final int COMPONENT_H = fmH * 2; //variável que auxilia
	private ScrollContainer sc;      //container principal que une os outros
	private ScrollContainer sc2;   //Menu de momentos
	private Label la;					
	private ImageControl fantasma;   // imagem usada para dizer falha
	private Container c;         	// barra inferior
	private Button cb1,cb2,cb3,cb4;			// botões da barra inferior
	private Button mb1,mb2,mb3,mb4;			// botões do menu de momentos
	private Button like1,coment1;			// quantidade de likes e coments
	
	
	// método auxiliar na adição de imagens ao programa
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*(size)), true));
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			System.out.println("cheguei");
			e.printStackTrace();
		}
		return image;
	} 
	
	//método utilizado para padronizar os containers criados
	public Container criarContainer(String Nome,String idioma, String text, ImageControl imagem,ImageControl foto,String [] buttonsNames,Image likee,Image coment,String qantLike, String qantComent) {
		contestx = new Container() {
			private Label nome,idiom,text2;
			private ButtonMenu bm3;
			public void initUI() {
				setNextTransitionEffect(TRANSITION_FADE );
				setBorderStyle(BORDER_SIMPLE);
				nome  = new Label(Nome);
				idiom = new Label(idioma);
				text2 = new Label(text,FILL);
				Image [] buttonsImages = {likee,coment};
				like1 = new Button(qantLike,buttonsImages[0],RIGHT,10);
				coment1 = new Button(qantComent,buttonsImages[1],RIGHT,10);
				like1.transparentBackground = true;
				coment1.transparentBackground = true;
				nome.setFont(Font.getFont(Font.NORMAL_SIZE-1));
				idiom.setFont(Font.getFont(Font.NORMAL_SIZE-2));
				add(imagem,LEFT+(SCREENSIZE+10),TOP+(COMPONENT_H/2), PREFERRED,PREFERRED);
				add(nome,LEFT+70,SAME-10, PREFERRED,PREFERRED);
				add(idiom,SAME,AFTER+5,PREFERRED,PREFERRED);
				add(text2,SAME,AFTER+5,PREFERRED,PREFERRED);
				add(foto,SAME,AFTER+5,PREFERRED,PREFERRED);
				add(like1,SAME,BOTTOM,PREFERRED,PREFERRED+10);
				add(coment1,AFTER+10,SAME,SAME,SAME);
			}	
		};
		contestx.setBackForeColors(BRIGHTER_BACKGROUND, Color.BLACK);
		return contestx;
	}
	
	
	
	public void initUI() {
		setBackColor(Color.WHITE);
		counts = new ArrayList<Container>();
		//adicionar fantasma e label porém deixar escondidos
		fantasma = passarIC("saco_01.jpg",7);
		fantasma.transparentBackground = true;
		add(fantasma,CENTER,CENTER,PREFERRED,PREFERRED);
		la = new Label("",CENTER);
		fantasma.setVisible(false);
		la.setVisible(false);
		add(la,LEFT,AFTER+10,FILL,COMPONENT_H);
		
		//barra superior
		bar1 = new Bar("Moments");
		add(bar1,LEFT,TOP,FILL,PREFERRED);
		bar1.addButton(passarIC("sinoX.png",1).getImage());
		bar1.addButton(passarIC("pena.png",1).getImage());
		setNextTransitionEffect(TRANSITION_FADE );
		
		//adição do menu dos momentos
		sc2 = new ScrollContainer() {
			public void initUI() {
				mb1 = new Button("Default");
				mb2 = new Button("Learn");
				mb3 = new Button("Following");
				mb4 = new Button("Classmates");
				mb1.transparentBackground = true;
				mb2.transparentBackground = true;
				mb3.transparentBackground = true;
				mb4.transparentBackground = true;
				mb1.setBorder(BORDER_NONE);
				mb2.setBorder(BORDER_NONE);
				mb3.setBorder(BORDER_NONE);
				mb4.setBorder(BORDER_NONE);
				add(mb1,LEFT,TOP,SCREENSIZE+30,FILL);
				add(mb2,AFTER,TOP,SAME,FILL);
				add(mb3,AFTER,TOP,SAME,FILL);
				add(mb4,AFTER,TOP,SAME,FILL);
			}
		};
		sc2.setBorderStyle(BORDER_LOWERED);
		add(sc2,LEFT,AFTER,LEFT+SCREENSIZE+100,SCREENSIZE+7);
		
		//dados estáticos dosc containers
		counts.add(criarContainer("Agah","PT > EN","Anyone here starting\ncollege in August?",passarIC("retrato1.jpg",2),passarIC("paisagem1.jpg",5),nomes,passarIC("coracao.jpg",1).getImage(),passarIC("nuvem.jpg",1).getImage(),"3","4"));
		counts.add(criarContainer("Risas","EN>PT","caught some flicks from\nDowntown, wjat y'all think?",passarIC("retrato2.jpg",2),passarIC("ic_dialog_info.png",0),nomes,passarIC("coracao.jpg",1).getImage(),passarIC("nuvem.jpg",1).getImage(),"52","10"));
		counts.add(criarContainer("Frankli","ES>CH","Deerfield beach Florida\nsunrise Sunday morning",passarIC("retrato3.jpg",2),passarIC("quadro.jpg",5),nomes,passarIC("coracao.jpg",1).getImage(),passarIC("nuvem.jpg",1).getImage(),"32","41"));
		counts.add(criarContainer("Joshua","CH>ES","just got this",passarIC("retrato1.jpg",2),passarIC("grupo.jpg",5),nomes,passarIC("coracao.jpg",1).getImage(),passarIC("nuvem.jpg",1).getImage(),"1","0"));

		//adicionar containers ao ScrollContainer Principal
		sc = new ScrollContainer() {
			public void initUI() {
				if(counts.size() != 0) {
					for(int i = 0;i<counts.size();i++) {
						if(i == 0) {
							add((Control) counts.get(i),LEFT,TOP,FILL,SCREENSIZE +50);
						}
						else
							add((Control) counts.get(i),LEFT,AFTER,FILL,SCREENSIZE +50);
					}
					}
				}
			
		};
		sc.setBorderStyle(BORDER_LOWERED);
		
		//adição da barra inferior
		c = new Container() {
			public void initUI() {
				Image nuvem = passarIC("nuvems.jpg",2).getImage();
				nuvem.applyColor(Color.getRGB(120,120,120));
				Image azul = passarIC("conexao.png",2).getImage();
				azul.applyColor2(Color.getRGB(56,176,222));
				cb1 = new Button("Talks",nuvem,BOTTOM,0);
				cb2 = new Button("Moments",azul,BOTTOM,0);
				cb3 = new Button("Search",passarIC("lupa.png",2).getImage(),BOTTOM,0);
				cb4 = new Button("Profile",passarIC("profileX.png",2).getImage(),BOTTOM,0);
				cb1.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb2.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb2.setForeColor(Color.getRGB(56,176,222));
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
		add(sc,LEFT,AFTER,FILL,FILL-50);
		add(c,LEFT,AFTER,FILL,FILL);
		}
	
	// método utilizado para tratar de eventos
	public void onEvent(Event event) {
		switch(event.type) {
		case ControlEvent.PRESSED:
			//mudar de tela
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
			//mudar de acordo com o menu de momentos
			if(event.target ==mb1) {
				sc.setVisible(true);
				fantasma.setVisible(false);
				la.setVisible(false);
			}
			if(event.target ==mb2){
				setBackColor(Color.getRGB(255, 255, 255));
				sc.setVisible(false);
				fantasma.setVisible(true);
				add(fantasma,CENTER,CENTER,PREFERRED,PREFERRED);
				la.setText("No moments yet");
				la.setVisible(true);
			}
			if (event.target ==mb3) {
				setBackColor(Color.getRGB(255, 255, 255));
				sc.setVisible(false);
				fantasma.setVisible(true);
				add(fantasma,CENTER,CENTER,PREFERRED,PREFERRED);
				la.setText("No Following moments yet");
				la.setVisible(true);
			}
				
			if (event.target ==mb4) {
				setBackColor(Color.getRGB(255, 255, 255));
				sc.setVisible(false);
				fantasma.setVisible(true);
				la.setText("No Classmate moments yet");
				la.setVisible(true);
			}
			
		}
		
	}
}



