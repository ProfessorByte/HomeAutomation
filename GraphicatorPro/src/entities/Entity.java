package entities;

import java.util.ArrayList;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Entity {

    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scaleX, scaleY, scaleZ;
    private ArrayList<Entity> entities;

    private int textureIndex = 0;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scaleX = this.scaleY = this.scaleZ = scale;
        this.entities = new ArrayList<>();
    }

    public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.textureIndex = index;
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scaleX = this.scaleY = this.scaleZ = scale;
        this.entities = new ArrayList<>();
    }
    
    public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scaleX, float scaleY, float scaleZ) {
        this.textureIndex = index;
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.entities = new ArrayList<>();
    }

    public float getTextureXOffset() {
        int column = textureIndex % model.getTexture().getNumberOfRows();
        return (float) column / (float) model.getTexture().getNumberOfRows();
    }

    public float getTextureYOffset() {
        int row = textureIndex / model.getTexture().getNumberOfRows();
        return (float) row / (float) model.getTexture().getNumberOfRows();
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
        
        for(Entity entity : entities){
            entity.increasePosition(dx, dy, dz);
        }
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
        
        for (Entity entity : entities){
            entity.increaseRotation(dx, dy, dz);
        }
    }

    public void increaseScale(float scale){
        increaseScale(scale, scale, scale);
    }

    public void increaseScale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX += scaleX;
        this.scaleY += scaleY;
        this.scaleZ += scaleZ;
        
        for (Entity entity : entities){
            entity.increaseScale(scaleX, scaleY, scaleZ);
        }
    }
    
    public void compose(Entity entity) {
        if (!entities.contains(entity)){
            this.entities.add(entity);
        }
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        float ans = -1.0f;
        if(scaleX == scaleY && scaleY == scaleZ){
            ans = scaleX;
        }
        return ans;
    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public float getScaleZ() {
        return scaleZ;
    }

    public void setScale(float scale) {
        this.scaleX = this.scaleY = this.scaleZ = scale;
    }
    
    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setScaleZ(float scaleZ) {
        this.scaleZ = scaleZ;
    }
    
    public ArrayList<Entity> getComposedObjects() {
        return entities;
    }

    public void setComposedObjects(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}