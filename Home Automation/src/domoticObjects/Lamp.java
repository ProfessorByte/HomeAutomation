package domoticObjects;

import entities.Entity;
import entities.Light;
import entities.Player;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Lamp extends Entity {
    
    private Light light;
    private Vector3f colour; 
    private float heightLight;
    
    public Lamp(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, Vector3f colour, Vector3f attenuation, float heightLight){
        super(model, position, rotX, rotY, rotZ, scale);
        this.light = new Light(new Vector3f(getPosition().x, getPosition().y+heightLight, getPosition().z), colour, attenuation);
        this.colour = colour;
        this.heightLight = heightLight;
    }
    
    public Lamp(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, Vector3f colour, float heightLight){
        super(model, position, rotX, rotY, rotZ, scale);
        this.light = new Light(new Vector3f(getPosition().x, getPosition().y+heightLight, getPosition().z), colour, new Vector3f(1, 0.01f, 0.002f));
        this.colour = colour;
        this.heightLight = heightLight;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public float getHeightLight() {
        return heightLight;
    }

    public void setHeightLight(float heightLight) {
        this.heightLight = heightLight;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
    
    public void on(){
        light.setColour(colour);
    }
    
    public void off(){
        light.setColour(new Vector3f(0, 0, 0));
    }
    
    public boolean isTrigger(float r, Player player){
        boolean ans = false;
        float x = player.getPosition().z;
        float y = player.getPosition().x;
        float h = getPosition().z;
        float k = getPosition().x;
        if(Math.pow(x - h, 2) + Math.pow(y - k, 2) <= Math.pow(r, 2)){
            ans = true;
        }
        return ans;
    }
}
