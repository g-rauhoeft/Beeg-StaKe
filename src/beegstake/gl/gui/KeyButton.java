package beegstake.gl.gui;
import beegstake.gl.gui.util.Point;

public class KeyButton extends ImageButton implements ICursorListener {

	private String buttonText;
	public KeyButton(Point position, int width, int height, String text){
		super(position, width, height);
		setButtonText(text);
	}
	
	@Override
	public void render() {
		super.render();
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

}
