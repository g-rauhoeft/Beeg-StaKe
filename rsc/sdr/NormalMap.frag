uniform sampler2D colourMap;
uniform sampler2D normalMap;
uniform vec2 mousePositions[20];
uniform vec3 mouse;
uniform int count;

void main()
{
	vec3 normal = normalize(texture2D(normalMap, gl_TexCoord[0].st).rgb * 2.0 - 1.0);  
	
	float diffuse = 0.0f;
	
	for(int i = 0; i < count; i++){
  		vec3 lightPosition = normalize(vec3(mousePositions[0],20)-gl_FragCoord.xyz);
		diffuse = diffuse + max(dot(normal, lightPosition), 0);
	}  
  
	gl_FragColor = diffuse * texture2D(colourMap, gl_TexCoord[0].st).rgba;  
}