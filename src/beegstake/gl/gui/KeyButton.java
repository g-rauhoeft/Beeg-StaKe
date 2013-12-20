package beegstake.gl.gui;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import beegstake.gl.gui.util.Point;

public class KeyButton extends ImageButton implements ICursorListener {

	private String buttonText;
	private Font font;
	private TrueTypeFont typeFont;
	private Point textPos;
	public KeyButton(Point position, int width, int height, String text){
		super(position, width, height);
		setButtonText(text);
		font = new Font("Times New Roman", Font.BOLD, 24);
		typeFont = new TrueTypeFont(font, false);
		setTextToButton();
	}
	
	public void setTextToButton(){
		System.out.println(typeFont.getWidth(buttonText)+" "+this.getWidth());
		this.textPos= new Point(getPosition().getX()+this.getWidth()/2-typeFont.getWidth(buttonText), (this.getHeight()-typeFont.getLineHeight())/2+getPosition().getY());
		System.out.println(textPos);
	}

	@Override
	public void render() {
		super.render();
		typeFont.drawString(this.textPos.getX(), this.textPos.getY(), getButtonText(), new Color(0,0,255));
		Color.white.bind();
	}
	
	@Override
	public void cursorOver(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cursorOut(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cursorMoved(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		if(buttonText == null){
			this.buttonText="";
		}
		this.buttonText = buttonText;
	}

}
