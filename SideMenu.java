package sidemenu;

// Imports Necessários
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.ui.*;

public class SideMenu extends MainWindow {
	
	public SideMenu(){
		super("", BORDER_NONE);
		Settings.uiStyle = Settings.Android;
		Settings.enableWindowTransitionEffects = true;
		Settings.fingerTouch = true;
	}
	
	
	
	//variáveis
	private final int COMPONENT_H = fmH * 2;
	private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight,Settings.screenWidth) * 0.10);
	private Button login,signUP;        //Butões de abrir a tela de login e de  redes sociais respectivamente
	private Label lb;					//Mensagem.
	private Button b1;					// botão que auxilia na volta para tela inicial.
	private Container signup;				// Container que aparecerá em meio a essa tela caso seja clicado no botão signUP
	private Button b2,b3,b4;		//botões dentro do container do signup
	private Button imgbutton1,imgbutton2,imgbutton3;   //butões de imagem.
	
	
	
	// Método que auxilia no incremento de imagens ao programa.
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*(size)), true));
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	} 
	
	
	public void initUI() {
				setNextTransitionEffect(TRANSITION_FADE );    //efeito de transição para a próxima tela.
				add(passarIC("hellotalk.png",30),LEFT,TOP,FILL,FILL);
				backColor = Color.getRGB(255, 255, 255);
				signUP = new Button("SignUP");
				signUP.setBackForeColors(Color.WHITE, Color.BLACK);
				signUP.setFont(Font.getFont(true, Font.NORMAL_SIZE+1));
				signUP.setBorder(BORDER_ROUNDED);
				lb = new Label("Already have an account?",LEFT);
				lb.transparentBackground = true;
				lb.setForeColor(Color.WHITE);
				login = new Button("Log In");
				login.setForeColor(Color.WHITE);
				login.transparentBackground = true;
				b1  = new Button("");
				b1.transparentBackground = true;
				add(b1,LEFT,TOP,FILL,FILL);
				add(signUP,LEFT+1, TOP + 350, FILL-1, COMPONENT_H);
				login.transparentBackground=true;
				add(lb,CENTER+40, AFTER + FLAT_EDGE_MARGIN -30,PARENTSIZE +100, SAME);
				add(login,SAME+164, AFTER + FLAT_EDGE_MARGIN -68,SAME-250, SAME);
			}
	
	
	// método utilizado para tratar de eventos
	public void onEvent(Event event)
    {
        switch (event.type)
        {
         case ControlEvent.PRESSED:
       	  if (event.target == signUP) { // container signup será adicionado a tela 
            		signup = new Container() {
            			public void initUI() {
            				setNextTransitionEffect(TRANSITION_FADE );
            				setBorderStyle(BORDER_SIMPLE);
            				Label su = new Label("Sign Up with...");
            				Image [] ic = {passarIC("email.jpg",1).getImage(),passarIC("facebook.jpg",1).getImage(),passarIC("wechat.png",1).getImage()};
            				b2 = new Button("Email");
            				b3 = new Button("Facebook");
            				b4 = new Button("WeChat");
            				imgbutton1 = new Button(ic[0]);
            				imgbutton2 = new Button(ic[1]);
            				imgbutton3 = new Button(ic[2]);
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
             		add(signup,LEFT,TOP +340,FILL,FILL);
            		break;
            	}
       	  else if((event.target ==b2)||(event.target ==b3)||(event.target ==b4)) {  //Caso clique em uma das redes sociais entrará direto no programa
     		  MainWindow.getMainWindow().swap(new AreaDoApp());
     		  break;
     	  }
       	 else if(event.target == login) {		//direcionado para tela de login
      	   MainWindow.getMainWindow().swap(new side1());
      	   break;  
      	   }
       	 else if(event.target == b1) {   //caso clique na imagem quando a tela de sign estiver aberta ela sumirá voltando a tela inicial completa
       		 signup.setVisible(false);
       		 MainWindow.getMainWindow().initUI();
       	 }
        }    
    }
}
          
	   
		
	
