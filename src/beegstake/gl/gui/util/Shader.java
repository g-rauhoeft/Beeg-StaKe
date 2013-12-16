package beegstake.gl.gui.util;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

public class Shader {
	private String fragmentShader, vertexShader;
	private int fragmentShaderId, vertexShaderId, programId;

	public Shader(String fragmentShader, String vertexShader) {
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;
		this.vertexShaderId = loadShader(ARBVertexShader.GL_VERTEX_SHADER_ARB,
				vertexShader);
		this.fragmentShaderId = loadShader(
				ARBFragmentShader.GL_FRAGMENT_SHADER_ARB, fragmentShader);
		this.programId = ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(this.programId, this.vertexShaderId);
		ARBShaderObjects.glAttachObjectARB(this.programId,
				this.fragmentShaderId);
		ARBShaderObjects.glLinkProgramARB(this.programId);
		ARBShaderObjects.glValidateProgramARB(this.programId);
	}

	private int loadShader(int type, String code) {
		int id = ARBShaderObjects.glCreateShaderObjectARB(type);
		if (id == 0){
			return 0;
		}
		ARBShaderObjects.glShaderSourceARB(id, code);
		ARBShaderObjects.glCompileShaderARB(id);
		if (ARBShaderObjects.glGetObjectParameteriARB(id,
				ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
			throw new RuntimeException("Error creating shader: "+code);
		return id;
	}

	public String getFragmentShader() {
		return fragmentShader;
	}

	public void setFragmentShader(String fragmentShader) {
		this.fragmentShader = fragmentShader;
	}

	public String getVertexShader() {
		return vertexShader;
	}

	public void setVertexShader(String vertexShader) {
		this.vertexShader = vertexShader;
	}

	public int getFragmentShaderId() {
		return fragmentShaderId;
	}

	public int getVertexShaderId() {
		return vertexShaderId;
	}

	public int getProgramId() {
		return programId;
	}

	private static String getLogInfo(int id) {
		return ARBShaderObjects.glGetInfoLogARB(id, ARBShaderObjects
				.glGetObjectParameteriARB(id,
						ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
	}
}
