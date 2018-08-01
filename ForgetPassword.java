package sidemenu;


//importes necessários
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
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

public class ForgetPassword extends Container{
	private final int COMPONENT_H = fmH * 2;  //variável de auxilio
	private Button voltar, send, imgX1;   //respectivamente: voltar para tela de login, enviar ao email e botão de apagar o escrito
	private Label lb;
	private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight, Settings.screenWidth) * 0.10); //variável de auxílio
	private Edit forget;     //Local onde será colocado o email
	private Label msg;		
	private Container c;	//"barra"
	private MessageBox mb;   // msg de link enviado ou não enviado ao email
	private ImageControl image1;
	
	// Método que auxilia no incremento de imagens ao programa.v
	public ImageControl passarIC(String imagem,float size) {
		ImageControl image = null;
		try {
			image = new ImageControl(new Image(imagem).hwScaledFixedAspectRatio((int)(fmH*(size)), true));
		} catch (IOException | ImageException e) {
			e.printStackTrace();
		}
		return image;
	} 
	
	
	public void initUI() {
		setBackColor(Color.WHITE);
		//criação da barra
		
		c = new Container() {
			public void initUI() {
				add(voltar = new Button("X"),LEFT,CENTER,FLAT_EDGE_MARGIN,PREFERRED);
				voltar.transparentBackground = true;
				add(lb = new Label("Forgot Password"),SAME+40,CENTER,SAME*2,SAME);
				add(send = new Button("Send"),RIGHT+FLAT_EDGE_MARGIN,CENTER,FLAT_EDGE_MARGIN*5,PREFERRED );
				send.transparentBackground = true;
			}
		};
		add(c, LEFT,TOP, FILL,COMPONENT_H);
		c.setBorderStyle(BORDER_RAISED);
		//criação da área de edit e envio
		image1 = new ImageControl(passarIC("email.jpg",2).getImage());
		add(image1,LEFT + FLAT_EDGE_MARGIN, AFTER+FLAT_EDGE_MARGIN, PREFERRED, COMPONENT_H);
		add(forget = new Edit(),CENTER, SAME, SCREENSIZE+50, COMPONENT_H/(3/2));
		forget.caption = "You Registered email";
		forget.setFont(Font.getFont(Font.NORMAL_SIZE-2));
		forget.captionColor = Color.getRGB(150, 150, 150);
		forget.transparentBackground = true;
		imgX1 = new Button(passarIC("xisV.png",1).getImage());
		imgX1.transparentBackground = true;
		imgX1.setBackForeColors(Color.BLACK, Color.BLACK);
		add(imgX1,CENTER+60,SAME+10,PREFERRED,PREFERRED);
		add(msg = new Label("HelloTalk will send a password reset\nlink to your registered email address.",LEFT), LEFT + FLAT_EDGE_MARGIN, AFTER+10, PREFERRED, COMPONENT_H);
		msg.setForeColor(Color.getRGB(150, 150, 150));
		msg.setFont(Font.getFont(Font.NORMAL_SIZE-2));
	}
	public void onEvent(Event event)
    {
        switch (event.type)
        {
        //linkar a butões à novas telas
         case ControlEvent.PRESSED:
       	  if (event.target == voltar) {
       		  MainWindow.getMainWindow().swap(new side1());
       	  }
       	  else if(event.target == send) {
       		  if(forget.getText().isEmpty() ==true) {  // msg box alertando algo(enviado ou não)
       			mb = new MessageBox("Message","Please, write your Email");
       			mb.popup();
       			break;
       		  }
       		  else {
       			System.out.println(forget.getText().length());
	       		mb = new MessageBox("Message","Link send to your email");
	       		mb.popup();
	       		break;
       		  }
       	  }
       	  //apagando o que tem escrito
       	  else if(event.target == imgX1) {
       		forget.setText("");
       	  }
        }
        
    }
}
