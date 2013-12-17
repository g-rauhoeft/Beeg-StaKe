package beegstake.gl.gui;
import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.TextPosition;

public class KeyButton extends ImageButton implements ICursorListener {

	private String buttonText;
	private Font font;
	private TrueTypeFont typeFont;
	private TextPosition textPos;
	public KeyButton(Point position, int width, int height, String text){
		super(position, width, height);
		setButtonText(text);
		font = new Font("Times New Roman", Font.BOLD, 24);
		typeFont = new TrueTypeFont(font, false);
		setTextToButton();
	}
	
	public void setTextToButton(){
		this.textPos= new TextPosition(this.getWidth()/2+getPosition().getX(), this.getHeight()/2+getPosition().getY());
	}

	@Override
	public void render() {
		super.render();
		typeFont.drawString(this.textPos.getX(), this.textPos.getY(), getButtonText(), new Color(20, 40, 50));
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
