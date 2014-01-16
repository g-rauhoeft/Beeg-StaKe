uniform sampler2D colourMap;
uniform sampler2D normalMap;
uniform vec3 mouse;

void main()
{
	vec3 normal = normalize(texture2D(normalMap, gl_TexCoord[0].st).rgb * 2.0 - 1.0);  
	
  	vec3 lightPosition = normalize(mouse-gl_FragCoord.xyz);
	float diffuse = max(dot(normal, lightPosition), 0);
  
	gl_FragColor = diffuse * texture2D(colourMap, gl_TexCoord[0].st).rgba;  
}