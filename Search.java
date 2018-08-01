package sidemenu;


//imports necessários
import java.util.ArrayList;
import totalcross.io.IOException;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
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

public class Search extends Container{
	//variáveis
	private Bar bar1;  //barra superior
	private Container c; //barra inferior
	private Button cb1,cb2,cb3,cb4,test; // botões da barra inferior e  botão que abre Menu de procuras
	private ScrollContainer cExtra;   //utilizado na função de criar um ScrollContainer de Containers
	private Container contestx, contest,contest2,contest3,contest4,contest5,contest6;   //dados estáticos
	private ArrayList<Container> best,Online,Nearest,Self;    //filas de diferentes tipos de procura
	private MenuBarDropDown mb1; //Menu de procuras
	private MenuItem[] mi = {new MenuItem(""),new MenuItem("Best Match"),new MenuItem("Online"),new MenuItem("Nearest"),new MenuItem("Self")}; //itens do menu de busca
	
	//método que cria um ScrollContainer de Containers
	public ScrollContainer criarScrollContainer(ArrayList<Container> generic) {
		cExtra = new ScrollContainer() {
			public void initUI() {
				for(int i = 0;i<generic.size();i++) {
					if(i == 0)
						add((Control) generic.get(i),LEFT,TOP,FILL,SCREENSIZE +15);
					else
						add((Control) generic.get(i),LEFT,AFTER,FILL,SCREENSIZE +15);
				}
			}
		};
		return cExtra;
	}
	
	
	//Criar um Container específico para facilitar a criação de diversos (já que os dados são estáticos)
	public Container criarContainer(String Nome,String idiom, String imagem,String local,String hora ) {
		contestx = new Container() {
			private Label nome,idioma,location;
			public void initUI() {
				ImageControl image;
				nome  = new Label(Nome);
				idioma = new Label(idiom);
				location = new Label(local);
				location.setFont(Font.getFont(false, Font.NORMAL_SIZE-2));
				location.setForeColor(Color.getRGB(120, 120, 120));
				Label horaDaMsg = new Label(hora);
				horaDaMsg.setFont(Font.getFont(Font.NORMAL_SIZE-4));
				horaDaMsg.setForeColor(Color.getRGB(120, 120, 120));
				image = passarIC(imagem,2);
				add(image,LEFT+10,CENTER, PREFERRED,PREFERRED);
				add(nome,LEFT+70,TOP+(SCREENSIZE+40), PREFERRED,PREFERRED);
				add(idioma,SAME,AFTER, PREFERRED,PREFERRED);
				add(location,SAME,AFTER,PREFERRED,PREFERRED);
				add(horaDaMsg,LEFT+(SCREENSIZE+90),SAME, PREFERRED,PREFERRED);
					
			}
		};
		contestx.setBorderStyle(BORDER_RAISED);
		return contestx;
	}
	// Método que auxilia no incremento de imagens ao programa.
	public ImageControl passarIC(String imagem,int size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*size), true));
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	
	
	public void initUI() {
		//inicialização dos Arrays
		setBackColor(Color.WHITE);
		best = new ArrayList<Container>();
		Online = new ArrayList<Container>();
		Nearest = new ArrayList<Container>();
		Self = new ArrayList<Container>();
		
		//barra superior
		bar1 = new Bar("Best Match +");
		bar1.canSelectTitle = true;
		mb1= new MenuBarDropDown(30,40,mi);
		bar1.addButton(passarIC("lupa.png",1).getImage());
		bar1.fillColor = Color.WHITE;
		add(bar1, LEFT, 0, FILL, PREFERRED);
		test =  new Button("");
		test.transparentBackground = true;
		add(test,LEFT,TOP+10,SCREENSIZE+30,SAME);
		setNextTransitionEffect(TRANSITION_FADE );
		
		//Perfils das pessoas
		contest = criarContainer("Pete", "EN > PT", "retrato1.jpg","Location Not Provided","15:00 PM");
		contest2 = criarContainer("Derik","EN > JP","retrato2.jpg","U.S.A","8:00 AM");
		contest3 = criarContainer("Kat","EN > PT","retrato3.jpg","Canada","8:00 AM");
		contest4 = criarContainer("Mateo","PT > EN","retrato2.jpg","Location Not Provided","Yesterday");
		contest5 = criarContainer("Jahaa","EN > PT","retrato1.jpg","Ottawa","Yesterday");
		contest6 = criarContainer("Sam","EN > JP","retrato2.jpg","Location Not Provided","Yesterday");
		
		//Adição aos tipos de buscas
		best.add(contest); best.add(contest2); best.add(contest3); best.add(contest4); best.add(contest5); best.add(contest6);
		Online.add(contest3); Online.add(contest2);Online.add(contest);Online.add(contest6);Online.add(contest5);Online.add(contest4);
		Nearest.add(contest);Nearest.add(contest2);Nearest.add(contest6);Nearest.add(contest5);Nearest.add(contest3);Nearest.add(contest4);
		Self.add(contest2);Self.add(contest);Self.add(contest3);Self.add(contest6);Self.add(contest5);Self.add(contest4);
		
		//criação do scrollContainer com a primeira lista(onde a tela se inicia)
		cExtra = criarScrollContainer(best);
		cExtra.setBorderStyle(BORDER_LOWERED);
		add(cExtra,LEFT,bar1.getHeight(),FILL,FILL-50);
		
		//barra inferior
		c = new Container() {
			public void initUI() {
				Image azul = passarIC("lupa.png",2).getImage();
				azul.applyColor2(Color.getRGB(56,176,222));
				Image nuvem = passarIC("nuvems.jpg",2).getImage();
				nuvem.applyColor(Color.getRGB(120,120,120));
				cb1 = new Button("Talks",nuvem,BOTTOM,0);
				cb2 = new Button("Moments",passarIC("conexao.png",2).getImage(),BOTTOM,0);
				cb3 = new Button("Search",azul,BOTTOM,0);
				cb4 = new Button("Profile",passarIC("profileX.png",2).getImage(),BOTTOM,0);
				cb1.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb2.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb3.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb3.setForeColor(Color.getRGB(56,176,222));
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
		add(c,LEFT,AFTER,FILL,FILL);
	}
	
	
	//método utilizado para tratar de eventos
	public void onEvent(Event event) {
		switch(event.type) {
		case ControlEvent.PRESSED:
			//mudar tela
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
			//abrir menu de procuras
			else if(event.target ==test) {
				mb1.popupNonBlocking ();
			}
			
			// mudar buscas
			else if(event.target == mb1 && mb1.getSelectedIndex() == 1) {
				bar1.setTitle("Best Match");
				remove(cExtra);
				cExtra = criarScrollContainer(best);
				cExtra.setBorderStyle(BORDER_LOWERED);
				add(cExtra,LEFT,TOP+bar1.getHeight(),FILL,FILL-50);
			}
			else if(event.target == mb1 && mb1.getSelectedIndex() == 2) {
				bar1.setTitle("Online+");
				remove(cExtra);
				cExtra = criarScrollContainer(Online);
				cExtra.setBorderStyle(BORDER_LOWERED);
				add(cExtra,LEFT,TOP+bar1.getHeight(),FILL,FILL-50);
			}
			else if(event.target == mb1 && mb1.getSelectedIndex() == 3) {
				bar1.setTitle("Nearest+");
				remove(cExtra);
				cExtra = criarScrollContainer(Nearest);
				cExtra.setBorderStyle(BORDER_LOWERED);
				add(cExtra,LEFT,TOP+bar1.getHeight(),FILL,FILL-50);
			}
			else if(event.target == mb1 && mb1.getSelectedIndex() == 4) {
				bar1.setTitle("SELF-INTRODUCTION+");
				remove(cExtra);
				cExtra = criarScrollContainer(Self);
				cExtra.setBorderStyle(BORDER_LOWERED);
				add(cExtra,LEFT,TOP+bar1.getHeight(),FILL,FILL-50);
			}
		}
	}
}
