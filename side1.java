package sidemenu;


// imports necessários.
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;


public class side1 extends Container{
	private Label lb;
	private MessageBox mb;  // para alertar login errado   
	private Edit edLogin, edPassword; // espaços para inserir email e senha
	private Button entrar,fpw,voltar,signup,b1; // respectivamente: botão de login, esqueci senha, voltar a tela inicial, abrir container de redes sociais e de fechar tela de rdes sociais.
	private Button b2,b3,b4; // botões de redes sociais
	private Button imgbutton1,imgbutton2,imgbutton3;  // botões imagens de redse sociais
	private Check check;  //relembre
	private ImageControl image1,image2;   //Conttroladores de imagem
	private Button imgX1,imgX2; //butões que apagam algo escrito
	private final int COMPONENT_H = fmH * 2; // variável de auxílio
	private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight, Settings.screenWidth) * 0.10);  //variável de auxílio
	private Container signUP; //container de redes sociais
	
	
	// método auxiliar na adição de imagens ao programa
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*(size)), true));
			System.out.println("chegueiaqw");
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			System.out.println("cheguei");
			e.printStackTrace();
		}
		return image;
	} 
	
	public void initUI() {
		setBackColor(Color.WHITE);
		setNextTransitionEffect(TRANSITION_FADE ); // efeito de transição
		Settings.enableWindowTransitionEffects = true;
		b1  = new Button("");
		b1.transparentBackground = true;
		add(b1,LEFT,TOP,FILL,FILL);
		
		//container "barra" utilizado na tela de login
		Container c = new Container() {
			public void initUI() {
				add(voltar = new Button("X"),LEFT,CENTER,FLAT_EDGE_MARGIN,PREFERRED);
				voltar.transparentBackground = true;
				add(lb = new Label("Log In"),SAME+40,CENTER,SAME*2,SAME);
				add(signup = new Button("Sign UP"),RIGHT+FLAT_EDGE_MARGIN,CENTER,FLAT_EDGE_MARGIN*5,PREFERRED );
				signup.transparentBackground = true;
			}
		};
		c.setBorderStyle(BORDER_LOWERED);
		add(c, LEFT,TOP, FILL,COMPONENT_H);
		
		//construção ta parte de login.
		image1 = new ImageControl(passarIC("email.jpg",2).getImage());
		add(image1,LEFT + FLAT_EDGE_MARGIN, AFTER+FLAT_EDGE_MARGIN, PREFERRED, COMPONENT_H);
		add(edLogin = new Edit(),CENTER, SAME, SCREENSIZE+50, COMPONENT_H/(3/2));
		edLogin.caption = "Login";
		edLogin.transparentBackground = true;
		imgX1 = new Button(passarIC("xisV.png",1).getImage());
		imgX1.transparentBackground = true;
		imgX1.setBackForeColors(Color.BLACK, Color.BLACK);
		add(imgX1,CENTER+60,SAME+10,PREFERRED,PREFERRED);
	
		//construção ta parte de senha
		image2 = new ImageControl(passarIC("cadeado.png",2).getImage());
		add(image2,LEFT + FLAT_EDGE_MARGIN+5, AFTER+FLAT_EDGE_MARGIN, PREFERRED, COMPONENT_H);
		add(edPassword = new Edit(), CENTER, SAME, SCREENSIZE+50, COMPONENT_H/(3/2));
		edPassword.caption = "Password";
		edPassword.setMode(Edit.PASSWORD_ALL);
		edPassword.transparentBackground = true;
		imgX2 = new Button(passarIC("xisV.png",1).getImage());
		imgX2.transparentBackground = true;
		imgX2.setBackForeColors(Color.BLACK, Color.BLACK);
		add(imgX2,CENTER+60,SAME+10,PREFERRED,PREFERRED);
		
		//check e botão de entrar
		add(check = new Check("Remember Password"), CENTER, AFTER + 20,PREFERRED, COMPONENT_H- 20);
		check.transparentBackground = true;
		check.transparentBackground = true;
		check.setFont(Font.getFont(Font.NORMAL_SIZE-2));
		check.setForeColor(Color.getRGB(150, 150, 150));
		add(entrar = new Button("Login"), CENTER +60, AFTER + FLAT_EDGE_MARGIN, FLAT_EDGE_MARGIN* 3, COMPONENT_H);
		entrar.setBackForeColors(Color.RED, Color.WHITE);
		entrar.setBorder(BORDER_ROUNDED);
		add(fpw = new Button("Forget Password"),LEFT+10,AFTER -30,FLAT_EDGE_MARGIN*5, SAME- 15);
		fpw.setFont(Font.getFont(Font.NORMAL_SIZE-2));
		fpw.transparentBackground = true;
		fpw.setForeColor(Color.getRGB(150, 150, 150));
	}
	
	// método utilizado para tratar de eventos
	 public void onEvent(Event event)
     {
         switch (event.type)
         {
          case ControlEvent.PRESSED:     
        	  	if (event.target == signup) {  
	          		signUP = new Container() {    // container signup será adicionado a tela  
	          			public void initUI() {
	          				setNextTransitionEffect(TRANSITION_FADE );
	        				setBorderStyle(BORDER_SIMPLE);
	        				Label su = new Label("Sign Up with...");
	        				b2 = new Button("Email");
	        				b3 = new Button("Facebook");
	        				b4 = new Button("WeChat");
	        				imgbutton1 = new Button(passarIC("email.jpg",1).getImage());
	        				imgbutton2 = new Button(passarIC("facebook.jpg",1).getImage());
	        				imgbutton3 = new Button(passarIC("wechat.png",1).getImage());
	        				b2.transparentBackground = true;
	        				b3.transparentBackground = true;
	        				b4.transparentBackground = true;
	        				imgbutton1.transparentBackground = true;
	        				imgbutton2.transparentBackground = true;
	        				imgbutton3.transparentBackground = true;
	          				su.setBackColor(Color.getRGB(255, 255, 255));
	          				setBorderStyle(BORDER_SIMPLE);
	          				add(su,LEFT, TOP, FILL, COMPONENT_H/2);
	          				add(imgbutton1,LEFT+5,AFTER+5,PREFERRED,PREFERRED);
	          				add(b2,LEFT+ SCREENSIZE+30,SAME,PREFERRED,PREFERRED);
	          				add(imgbutton2,LEFT+5,AFTER+10,PREFERRED,PREFERRED);
	          				add(b3,LEFT+ SCREENSIZE+30,SAME,PREFERRED,PREFERRED);
	          				add(imgbutton3,LEFT+5,AFTER+10,PREFERRED,PREFERRED);
	          				add(b4,LEFT+ SCREENSIZE+30,SAME,PREFERRED,PREFERRED);
	          				backColor = Color.getRGB(255, 255, 255);
	          			}};	
	          		backColor = Color.getRGB(240, 240, 240);
	          		check.setBackColor(Color.getRGB(240, 240, 240));
	           		add(signUP,LEFT,TOP +350,FILL,FILL);
	          		break;
	          	}
     
        	  	else if (event.target == check) { //edição do check
		    		  if(check.isChecked()) {
		        		  check.setBackColor(Color.RED);
		        		  check.checkColor = Color.WHITE;
		        		  break;
		    		  }
		    		  else {
		    			  check.setBackColor(Color.WHITE);
		    		  }
		        }
        	  	
	        	else if(event.target == imgX1) {
	        		  edLogin.setText("");	  
	        	}
	        	else if(event.target == imgX2) {
	        		  edPassword.setText("");
	        	}
        	  	
        	  	
        	  	//linkagem dos botões com as próximas telas
	        	else if (event.target == entrar) { //verificação de login e senha, caso errado aparecerá uma message box.
	        		  if(edLogin.getText().equals("mario") && edPassword.getText().equals("123")) {
	        			  MainWindow.getMainWindow().swap(new AreaDoApp());
	            		  break;
	        		  }
	        		  else {
	        			  mb = new MessageBox("","Login or Password incorrect");
	         			  mb.popup();
	        		  }
        	    }
	        	else if (event.target == voltar) {
	        		  MainWindow.getMainWindow().swap(getParentWindow()); 
	        		  break;
	        	}
	        	else if (event.target == fpw) {
	        		  MainWindow.getMainWindow().swap(new ForgetPassword());
	        		  break;
	        	}
	        	else if (event.target == b1) {
        		  	  MainWindow.getMainWindow().swap(new side1());
        	    }
	        	else if((event.target ==b2)||(event.target ==b3)||(event.target ==b4)) {
	           		  MainWindow.getMainWindow().swap(new AreaDoApp());
	           		  break;
	           	}
         }
     }

}