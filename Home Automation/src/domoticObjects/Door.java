package domoticObjects;

import entities.Entity;
import entities.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Door extends Entity{
    private float rotationY;
    
    public Door(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale){
        super(model, position, rotX, rotY, rotZ, scale);
        rotationY = rotY;
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

    public float getRotationY() {
        return rotationY;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }
    
    
}
