package beegstake.gl.gui;

import beegstake.gl.gui.font.FontRenderer;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.system.Configuration;

public class Label extends RectComponent {

	private FontRenderer renderer;

	private Label(Point position, int width, int height) {
		super(position, width, height);
	}

	public Label(Point position, String text, boolean flipped) {
		this(position, 0, 0);
		String fontPath = Configuration.getConfiguration("System").getString("DefaultFontPath");
		String fontFile = Configuration.getConfiguration("System").getString("DefaultFontFile");
		renderer = new FontRenderer(text, ResourceManager.getFont(fontPath, fontFile), position, flipped);
		this.setText(text);
	}

	private void setDimensions() {
		this.setWidth(renderer.getImage().getWidth());
		this.setHeight(renderer.getImage().getHeight());
	}

	@Override
	public void render() {
		renderer.render();
	}

	public FontRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(FontRenderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public void setPosition(Point position){
		super.setPosition(position);
		if(this.renderer != null)
			this.renderer.setPosition(position);
	}

	public String getText() {
		return renderer.getText();
	}

	public void setText(String text) {
		renderer.setText(text);
		this.setDimensions();
	}
}
