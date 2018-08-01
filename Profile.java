package sidemenu;

//imports necessários
import totalcross.io.IOException;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Profile extends Container{
	private Bar bar1;  //barra superior
	private Container profile, atividades, logout, introduction; //subcontainers
	private Label lb1,lb2,lb3,lb4; 
	private Container c; // barra inferior
	private Button cb1,cb2,cb3,cb4;; //botões da barra inferior
	private Button img1Ativ,img2Ativ,img3Ativ,img4Ativ; // botoes de atividades
	private MessageBox mb; //mostra informações sobre as atividades
	
	//necessários na introdução
	private Button introductionButton;
	private Label lbintro1,lbintro2;
	
	private Button sair; //botão de logout
	private ScrollContainer sc; //container central
	
	// método auxiliar na adição de imagens ao programa
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*size), true));
		} catch (IOException | ImageException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	
	public void initUI() {
		setBackColor(Color.WHITE);
		setNextTransitionEffect(TRANSITION_FADE );
		
		//criação do container profile		
		profile = new Container() {
			public void initUI() {
				setBorderStyle(BORDER_SIMPLE);
				borderColor = Color.getRGB(200, 200, 200);
				lb1 = new Label("Mario Evandro");
				lb2 = new Label("@_amarioevandro");
				lb3 = new Label("PT  >  EN");
				lb4 = new Label("        1        |        1           \nFollowing   Followers");
				lb1.setFont(Font.getFont(true,Font.NORMAL_SIZE-1));
				lb2.setFont(Font.getFont(true,Font.NORMAL_SIZE-3));
				lb3.setFont(Font.getFont(true,Font.NORMAL_SIZE-2));
				lb2.setForeColor(Color.getRGB(150, 150, 150));
				lb4.setFont(Font.getFont(true,Font.NORMAL_SIZE-3));
				lb4.setForeColor(Color.getRGB(150, 150, 150));
				add(passarIC("retrato1.jpg",3),LEFT+SCREENSIZE+15,TOP+5,PREFERRED,PREFERRED);
				add(lb1,SAME,AFTER,PREFERRED,SCREENSIZE+5);
				add(lb2,SAME,AFTER,PREFERRED,SAME);
				add(lb3,SAME,AFTER,PREFERRED,SAME);
				add(lb4,CENTER+60,TOP+20,PREFERRED,PREFERRED);
			}
		};
		
		//criação do container atividades
		atividades = new Container() {
			public void initUI() {
				setBorderStyle(BORDER_SIMPLE);
				borderColor = Color.getRGB(200, 200, 200);
				img1Ativ = new Button("0",passarIC("traducao.png",1).getImage(),RIGHT,10);
				img2Ativ = new Button("1",passarIC("abc.jpg",1).getImage(),RIGHT,10);
				img3Ativ = new Button("5",passarIC("nota.png",1).getImage(),RIGHT,10);
				img4Ativ = new Button("6",passarIC("transcription.png",1).getImage(),RIGHT,10);
				add(img1Ativ,LEFT + SCREENSIZE+15,TOP+20,PREFERRED,PREFERRED);
				add(img2Ativ,AFTER+15,SAME,PREFERRED,SAME);
				add(img3Ativ,AFTER+15,SAME,PREFERRED,SAME);
				add(img4Ativ,AFTER+15,SAME,PREFERRED,SAME);
			}
		};
		
		//criação do container introduction
		introduction = new Container() {
			public void initUI() {
				setBorderStyle(BORDER_SIMPLE);
				borderColor = Color.getRGB(200, 200, 200);
				lbintro1 = new Label("SELF-INTRODUCTION");
				lbintro1.setForeColor(Color.getRGB(150, 150, 150));
				lbintro1.setFont(Font.getFont(Font.NORMAL_SIZE-1));
				lbintro2 = new Label("Add Bio and Audio Bio so more so more\n users can find you",LEFT);
				
				introductionButton = new Button("");
				introductionButton.transparentBackground = true;
				add(lbintro1,LEFT+10,TOP+10,PREFERRED,PREFERRED);
				add(lbintro2,SAME,AFTER,PREFERRED,PREFERRED);
				add(introductionButton,LEFT,TOP,FILL,FILL);	
			}
		};
		
		//criação do container logout
		logout = new Container() {
			public void initUI() {
				setBorderStyle(BORDER_SIMPLE);
				borderColor = Color.getRGB(200, 200, 200);
				sair = new Button("Log Out");
				sair.transparentBackground = true;
				sair.setForeColor(Color.RED);
				add(sair, CENTER, CENTER, PREFERRED, PREFERRED);
			}
		};
		
		//junção dos containers no principal
		sc = new ScrollContainer() {
			public void initUI() {
				add(profile,LEFT,AFTER+5,FILL,SCREENSIZE+30);
				add(atividades,SAME,AFTER+5,FILL,SCREENSIZE+15);
				add(introduction,SAME,AFTER+5,FILL,SAME);
				add(logout,SAME,AFTER+5,FILL,SCREENSIZE+10);
			}
		};
		sc.setBorderStyle(BORDER_LOWERED);
		
		//barra superior
		bar1 = new Bar("Profile");
		bar1.fillColor = Color.WHITE;
		add(bar1 = new Bar("Profile"),LEFT,TOP,FILL,PREFERRED);
		add(sc,LEFT,AFTER,FILL,FILL-50);
		setNextTransitionEffect(TRANSITION_FADE );
		
		// barra inferior
		c = new Container() {
			public void initUI() {
				Image nuvem = passarIC("nuvems.jpg",2).getImage();
				nuvem.applyColor(Color.getRGB(120,120,120));
				Image azul = passarIC("profileX.png",2).getImage();
				azul.applyColor2(Color.getRGB(56,176,222));
				cb1 = new Button("Talks",nuvem,BOTTOM,0);
				cb2 = new Button("Moments",passarIC("conexao.png",2).getImage(),BOTTOM,0);
				cb3 = new Button("Search",passarIC("lupa.png",2).getImage(),BOTTOM,0);
				cb4 = new Button("Profile",azul,BOTTOM,0);
				cb1.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb2.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb3.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb4.setFont(Font.getFont(Font.NORMAL_SIZE-3));
				cb4.setForeColor(Color.getRGB(56,176,222));
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
	
	
	// método utilizado para tratar de eventos
	public void onEvent(Event event) {
		switch(event.type) {
		case ControlEvent.PRESSED:
			if(event.target ==sair) {
				MainWindow.getMainWindow().swap(new side1());
			}
			//aparecer messageBox
			else if(event.target ==img1Ativ) {
				String i = "Translation used: " +img1Ativ.getText() + " times";
				mb = new MessageBox("",i);
				mb.setBackForeColors(Color.WHITE, Color.BLACK);
				mb.headerColor = Color.WHITE;
       			mb.popup();
			}
			else if(event.target ==img2Ativ) {
				String i = "Corrections Made: " +img2Ativ.getText();
				mb = new MessageBox("",i);
				mb.headerColor = Color.WHITE;
				mb.setBackForeColors(Color.WHITE, Color.BLACK);
       			mb.popup();			}
			else if(event.target ==img3Ativ) {
				String i = "Setences Favorites: " +img3Ativ.getText();
				mb = new MessageBox("",i);
				mb.headerColor = Color.WHITE;
				mb.setBackForeColors(Color.WHITE, Color.BLACK);
       			mb.popup();
			}
			else if(event.target ==img4Ativ) {
				String i = "Transcription Functions used: " +img4Ativ.getText() + " times";
				mb = new MessageBox("",i);
				mb.titleColor = Color.WHITE;
				mb.fillColor = Color.WHITE;
				mb.setBackForeColors(Color.WHITE, Color.BLACK);
       			mb.popup();
			}
			//mudar telas
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
		}
	}
}
