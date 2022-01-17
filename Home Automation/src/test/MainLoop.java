package test;

import auxiliaryElements.AuxComponent;
import dataStructures.ListaCDE;
import domoticObjects.Door;
import domoticObjects.Lamp;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
//import fontMeshCreator.FontType;
//import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiRenderer;
import guis.GuiTexture;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import objConverter.OBJFileLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import stats.PanelModEstadistico;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;
///import water.WaterFrameBuffers;
///import water.WaterRenderer;
///import water.WaterShader;
///import water.WaterTile;

public class MainLoop {

    private static List<Terrain> terrains;
    private static List<Entity> entities;
    private static List<Entity> normalMapEntities;
    private static List<Light> lights;
    private static List<GuiTexture> guiTextures;
    ///private static List<WaterTile> waters;

    private static ListaCDE<Entity> elementsList;

    private static Loader loader;
    private static MasterRenderer renderer;

    private static MousePicker picker;
    private static Camera camera;
    private static GuiRenderer guiRenderer;
    
    private static int cDoors = 0;
    private static int cLights = 0;
    private static int cantLights = 0;
    
    private static boolean[][] doubleFlag = {{false, false}, {false, false}};

    public static void main(String[] args) {

        terrains = new ArrayList<>();
        entities = new ArrayList<>();
        normalMapEntities = new ArrayList<>();
        lights = new ArrayList<>();
        guiTextures = new ArrayList<>();
        ///waters = new ArrayList<>();

        DisplayManager.createDisplay("Home Automation");
        loader = new Loader();
        TextMaster.init(loader);
        renderer = new MasterRenderer(loader);

        // *********TERRAIN TEXTURE STUFF**********
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("path"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("grassy3"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");
        terrains.add(terrain);
        // *****************************************

        TexturedModel rocks = new TexturedModel(OBJFileLoader.loadOBJ("rocks", loader), new ModelTexture(loader.loadTexture("rocks")));
        entities.add(new Entity(rocks, new Vector3f(75, 4.6f, -75), 0, 0, 0, 75));

        ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
        fernTextureAtlas.setNumberOfRows(2);
        TexturedModel fern = new TexturedModel(OBJFileLoader.loadOBJ("fern", loader), fernTextureAtlas);
        fern.getTexture().setHasTransparency(true);
        entities.add(new Entity(fern, 0, new Vector3f(10, terrain.getHeightOfTerrain(10, -140), -140), 0, 0, 0, 1));

        TexturedModel bobble = new TexturedModel(OBJFileLoader.loadOBJ("pine", loader), new ModelTexture(loader.loadTexture("pine")));
        bobble.getTexture().setHasTransparency(true);
        entities.add(new Entity(bobble, new Vector3f(140, terrain.getHeightOfTerrain(140, -140), -140), 0, 0, 0, 1));

        TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader), new ModelTexture(loader.loadTexture("lamp")));
        lamp.getTexture().setUseFakeLighting(true);
        Entity lamp1 = new Entity(lamp, new Vector3f(10, terrain.getHeightOfTerrain(10, -10), -10), 0, 0, 0, 1);
        entities.add(lamp1);
        lights.add(new Light(new Vector3f(10, terrain.getHeightOfTerrain(10, -10) + 15, -10), new Vector3f(0, 2, 0), new Vector3f(1, 0.01f, 0.002f)));

        //******************NORMAL MAP MODELS************************
        TexturedModel barrelModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("barrel", loader), new ModelTexture(loader.loadTexture("barrel")));
        barrelModel.getTexture().setNormalMap(loader.loadTexture("barrelNormal"));
        barrelModel.getTexture().setShineDamper(10);
        barrelModel.getTexture().setReflectivity(0.5f);
        Entity entity = new Entity(barrelModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);

        TexturedModel crateModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("crate", loader), new ModelTexture(loader.loadTexture("crate")));
        crateModel.getTexture().setNormalMap(loader.loadTexture("crateNormal"));
        crateModel.getTexture().setShineDamper(1);
        crateModel.getTexture().setReflectivity(0.5f);
        Entity entityWall = new Entity(crateModel, new Vector3f(0, 0, 0), 0, 0, 0, 0.04f);

        TexturedModel chair = new TexturedModel(NormalMappedObjLoader.loadOBJ("Chair", loader), new ModelTexture(loader.loadTexture("Chair")));
        chair.getTexture().setNormalMap(loader.loadTexture("chairNormal"));
        chair.getTexture().setShineDamper(10);
        chair.getTexture().setReflectivity(0.5f);
        Entity entity2 = new Entity(chair, new Vector3f(0, 0, 0), 0, 0, 0, 1f);

        TexturedModel lamp2 = new TexturedModel(NormalMappedObjLoader.loadOBJ("lampara", loader), new ModelTexture(loader.loadTexture("lampara1")));
        lamp2.getTexture().setNormalMap(loader.loadTexture("lamparaNormal"));
        lamp2.getTexture().setHasTransparency(true);
        lamp2.getTexture().setShineDamper(1);
        lamp2.getTexture().setReflectivity(0.5f);
        Lamp entity4 = new Lamp(lamp2, new Vector3f(0, 0, 0), 0, 0, 0, 1f, new Vector3f(1, 1, 0), 5);

        TexturedModel doorModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("puerta", loader), new ModelTexture(loader.loadTexture("puerta")));
        doorModel.getTexture().setNormalMap(loader.loadTexture("puertaNormal"));
        doorModel.getTexture().setShineDamper(10);
        doorModel.getTexture().setReflectivity(0.5f);
        Door entity5 = new Door(doorModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel windowModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("window", loader), new ModelTexture(loader.loadTexture("window")));
        windowModel.getTexture().setNormalMap(loader.loadTexture("windowNormal"));
        windowModel.getTexture().setShineDamper(10);
        windowModel.getTexture().setReflectivity(0.5f);
        Entity entity6 = new Entity(windowModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel sofaModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("sofa", loader), new ModelTexture(loader.loadTexture("sofa")));
        sofaModel.getTexture().setNormalMap(loader.loadTexture("sofaNormal"));
        sofaModel.getTexture().setShineDamper(10);
        sofaModel.getTexture().setReflectivity(0.5f);
        Entity entity8 = new Entity(sofaModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel computerModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("computer", loader), new ModelTexture(loader.loadTexture("computer")));
        computerModel.getTexture().setNormalMap(loader.loadTexture("computerNormal"));
        computerModel.getTexture().setShineDamper(10);
        computerModel.getTexture().setReflectivity(0.5f);
        Entity entity9 = new Entity(computerModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel bedModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("bed", loader), new ModelTexture(loader.loadTexture("bed")));
        bedModel.getTexture().setNormalMap(loader.loadTexture("computerNormal"));
        bedModel.getTexture().setShineDamper(10);
        bedModel.getTexture().setReflectivity(0.5f);
        Entity entity10 = new Entity(bedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel deskModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("escritorio", loader), new ModelTexture(loader.loadTexture("escritorio")));
        deskModel.getTexture().setNormalMap(loader.loadTexture("escritorioNormal"));
        deskModel.getTexture().setShineDamper(10);
        deskModel.getTexture().setReflectivity(0.5f);
        Entity entity11 = new Entity(deskModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel tableModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("table", loader), new ModelTexture(loader.loadTexture("escritorio")));
        tableModel.getTexture().setNormalMap(loader.loadTexture("escritorioNormal"));
        tableModel.getTexture().setShineDamper(10);
        tableModel.getTexture().setReflectivity(0.5f);
        Entity entity12 = new Entity(tableModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);
        
        TexturedModel focoModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("foco", loader), new ModelTexture(loader.loadTexture("foco")));
        focoModel.getTexture().setNormalMap(loader.loadTexture("windowNormal"));
        focoModel.getTexture().setShineDamper(10);
        focoModel.getTexture().setReflectivity(0.5f);
        Lamp entity7 = new Lamp(focoModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f, new Vector3f(1, 1, 1), -1);

        TexturedModel boulderModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("boulder", loader), new ModelTexture(loader.loadTexture("boulder")));
        boulderModel.getTexture().setNormalMap(loader.loadTexture("boulderNormal"));
        boulderModel.getTexture().setShineDamper(10);
        boulderModel.getTexture().setReflectivity(0.5f);
        Entity entity3 = new Entity(boulderModel, new Vector3f(0, 0, 0), 0, 0, 0, 1f);

        ///lights.add(new Light(new Vector3f(75, terrain.getHeightOfTerrain(75, -75) + 10, -75), new Vector3f(2, 0, 0)));
        ///lights.add(new Light(new Vector3f(75, terrain.getHeightOfTerrain(75, -75) + 1, -75), new Vector3f(0, 3, 0)));
        //************ENTITIES*******************
        normalMapEntities.add(entity);
        normalMapEntities.add(entity2);
        normalMapEntities.add(entityWall);
        normalMapEntities.add(entity3);
        normalMapEntities.add(entity4);
        normalMapEntities.add(entity5);
        normalMapEntities.add(entity6);
        normalMapEntities.add(entity7);
        normalMapEntities.add(entity8);
        normalMapEntities.add(entity9);
        normalMapEntities.add(entity10);
        normalMapEntities.add(entity11);
        normalMapEntities.add(entity12);

        //*******************OTHER SE   TUP***************
        Light sun = new Light(new Vector3f(10000, 10000, -10000), new Vector3f(1.3f, 1.3f, 1.3f));
        lights.add(sun);

        RawModel personModel = OBJLoader.loadObjModel("person", loader);
        TexturedModel person = new TexturedModel(personModel, new ModelTexture(loader.loadTexture("playerTexture")));
        Player player = new Player(person, new Vector3f(10, 5, -75), 0, 100, 0, 0.6f);
        entities.add(player);

        camera = new Camera(player);
        guiRenderer = new GuiRenderer(loader);
        picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);

        //**********Water Renderer Set-up************************
        ///WaterFrameBuffers buffers = new WaterFrameBuffers();
        ///WaterShader waterShader = new WaterShader();
        ///WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), buffers);
        ///List<WaterTile> waters = new ArrayList<WaterTile>();
        ///WaterTile water = new WaterTile(75, -75, 1);
        ///waters.add(water);
        //****************Game Loop Below*********************
        AuxComponent aux = new AuxComponent();
        elementsList = new ListaCDE<>();
        elementsList.insertar(entityWall);
        elementsList.insertar(entity2);
        elementsList.insertar(entity);
        elementsList.insertar(entity3);
        elementsList.insertar(entity4);
        elementsList.insertar(entity5);
        elementsList.insertar(entity6);
        elementsList.insertar(entity7);
        elementsList.insertar(entity8);
        elementsList.insertar(entity9);
        elementsList.insertar(entity10);
        elementsList.insertar(entity11);
        elementsList.insertar(entity12);
        int index = 0;

        while (!Display.isCloseRequested()) {

            Entity currentEntity = elementsList.acceder(index);
            player.move(terrain);
            camera.move();
            picker.update();
            
            for(int i = 0; i < doubleFlag.length; i++){
                if((doubleFlag[i][0] && doubleFlag[i][1]) || (!doubleFlag[i][0] && doubleFlag[i][1])){
                    doubleFlag[i][0] = false;
                    doubleFlag[i][1] = false;
                }
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_L)){
                PanelModEstadistico pan = new PanelModEstadistico(cDoors, cLights, cantLights);
                pan.stats();
                Keyboard.destroy();
                try {
                    Keyboard.create();
                } catch (LWJGLException ex) {
                    Logger.getLogger(MainLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (aux.getFlag() && currentEntity instanceof Lamp && currentEntity.equals(entity4)) {
                ((Lamp) currentEntity).getLight().setPosition(entity4.getPosition());
                if (!lights.contains(((Lamp) currentEntity).getLight())) {
                    lights.add(((Lamp) currentEntity).getLight());
                }
            } else if (lights.contains(((Lamp) entity4).getLight())) {
                lights.remove(((Lamp) (entity4)).getLight());
            }
            
            if (aux.getFlag() && currentEntity instanceof Lamp && currentEntity.equals(entity7)) {
                ((Lamp) currentEntity).getLight().setPosition(entity7.getPosition());
                if (!lights.contains(((Lamp) currentEntity).getLight())) {
                    lights.add(((Lamp) currentEntity).getLight());
                }
            } else if (lights.contains(((Lamp) entity7).getLight())) {
                lights.remove(((Lamp) (entity7)).getLight());
            }

            /*if (currentEntity instanceof Door){
                if(((Door) currentEntity).isTrigger(5, player) && currentEntity.getRotY() <= 90){
                    currentEntity.increaseRotation(0, 1, 0);
                } else  if (currentEntity.getRotY() > 0){
                    currentEntity.increaseRotation(0, -1, 0);
                }
            }*/
            for (int i = 0; i < normalMapEntities.size(); i++) {
                if (normalMapEntities.get(i) instanceof Door && !normalMapEntities.get(i).equals(currentEntity)) {
                    if (((Door) normalMapEntities.get(i)).isTrigger(8, player) && normalMapEntities.get(i).getRotY() <= ((Door) normalMapEntities.get(i)).getRotationY() + 90) {
                        normalMapEntities.get(i).increaseRotation(0, 1, 0);
                        if (!doubleFlag[0][0] && !doubleFlag[0][1]){
                            doubleFlag[0][0] = true;
                            cDoors++;
                        }
                    } else if (normalMapEntities.get(i).getRotY() > ((Door) normalMapEntities.get(i)).getRotationY()) {
                        normalMapEntities.get(i).increaseRotation(0, -1, 0);
                        if(doubleFlag[0][0] && !doubleFlag[0][1]){
                            doubleFlag[0][1] = true;
                        }
                    }
                }
            }
            
            for (int i = 0; i < normalMapEntities.size(); i++) {
                if (normalMapEntities.get(i) instanceof Lamp) {
                    if (((Lamp) normalMapEntities.get(i)).isTrigger(20, player)) {
                        ((Lamp) normalMapEntities.get(i)).on();
                        if(!doubleFlag[1][0] && !doubleFlag[1][1]){
                            doubleFlag[1][0] = true;
                            cLights++;
                        }
                    } else {
                        ((Lamp) normalMapEntities.get(i)).off();
                        if(doubleFlag[1][0] && !doubleFlag[1][1]){
                            doubleFlag[1][1] = true;
                        }
                    }
                }
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_N) && aux.getFlag()) {
                index++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (normalMapEntities.contains(currentEntity)) {
                    normalMapEntities.remove(currentEntity);
                    normalMapEntities.remove(currentEntity);
                    normalMapEntities.add(elementsList.acceder(index));
                } else {
                    entities.remove(currentEntity);
                    entities.remove(currentEntity);
                    entities.add(elementsList.acceder(index));
                }
            } else if (Keyboard.isKeyDown(Keyboard.KEY_M) && aux.getFlag()) {
                index--;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (index < 0) {
                    index = elementsList.longitud() - 1;
                }
                if (normalMapEntities.contains(currentEntity)) {
                    normalMapEntities.remove(currentEntity);
                    normalMapEntities.remove(currentEntity);
                    normalMapEntities.add(elementsList.acceder(index));
                } else {
                    entities.remove(currentEntity);
                    entities.remove(currentEntity);
                    entities.add(elementsList.acceder(index));
                }
            }
            moveAccordPositionMouse(currentEntity, aux);

            ///entity.increaseRotation(0, 1, 0);
            ///entity2.increaseRotation(0, 1, 0);
            GL11.glEnable(GL30.GL_CLIP_DISTANCE0);

            //render reflection teture
            ///buffers.bindReflectionFrameBuffer();
            float distance = 2 * (camera.getPosition().y);
            camera.getPosition().y -= distance;
            camera.invertPitch();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, 1));
            camera.getPosition().y += distance;
            camera.invertPitch();

            //render refraction texture
            ///buffers.bindRefractionFrameBuffer();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 0));

            //render to screen
            GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
            ///buffers.unbindCurrentFrameBuffer();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));
            ///waterRenderer.render(waters, camera, sun);
            guiRenderer.render(guiTextures);
            TextMaster.render();

            DisplayManager.updateDisplay();
        }

        //*********Clean Up Below**************
        TextMaster.cleanUp();
        ///buffers.cleanUp();
        ///waterShader.cleanUp();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
        System.exit(0);
    }

    private static void moveAccordPositionMouse(Entity entity, AuxComponent aux) {
        if (picker.getCurrentTerrainPoint() != null && aux.getFlag()) {
            entity.setPosition(new Vector3f(picker.getCurrentTerrainPoint().x, aux.getPositionY(), picker.getCurrentTerrainPoint().z));
            if (normalMapEntities.contains(entity)) {
                if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
                    Keyboard.destroy();
                    try {
                        Keyboard.create();
                    } catch (LWJGLException ex) {
                        Logger.getLogger(MainLoop.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (entity instanceof Door) {
                        normalMapEntities.add(new Door(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()));
                    } else if (entity instanceof Lamp) {
                        Light light = new Light(((Lamp) entity).getLight().getPosition(), ((Lamp) entity).getLight().getColour(), ((Lamp) entity).getLight().getAttenuation());
                        lights.add(light);
                        Lamp lamp = new Lamp(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale(), ((Lamp) entity).getColour(), ((Lamp) entity).getHeightLight());
                        lamp.setLight(light);
                        normalMapEntities.add(lamp);
                        cantLights++;
                    } else {
                        normalMapEntities.add(new Entity(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()));
                    }
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
                    normalMapEntities.remove(entity);
                    if (entity instanceof Lamp) {
                        lights.remove(((Lamp) entity).getLight());
                    }
                    aux.setFlag(false);
                }
            } else {
                if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
                    Keyboard.destroy();
                    try {
                        Keyboard.create();
                    } catch (LWJGLException ex) {
                        Logger.getLogger(MainLoop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (entity instanceof Door) {
                        entities.add(new Door(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()));
                    } else if (entity instanceof Lamp) {
                        Light light = new Light(((Lamp) entity).getLight().getPosition(), ((Lamp) entity).getLight().getColour(), ((Lamp) entity).getLight().getAttenuation());
                        lights.add(light);
                        Lamp lamp = new Lamp(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale(), ((Lamp) entity).getColour(), ((Lamp) entity).getHeightLight());
                        lamp.setLight(light);
                        entities.add(lamp);
                    } else {
                        entities.add(new Entity(entity.getModel(), entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()));
                    }
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
                    entities.remove(entity);
                    if (entity instanceof Lamp) {
                        lights.remove(((Lamp) entity).getLight());
                    }
                    aux.setFlag(false);
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                entity.increaseRotation(0, 1, 0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
                entity.increaseScale(0.001f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
                entity.increaseScale(-0.001f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                aux.setPositionY(aux.getPositionY() + 0.1f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
                aux.setPositionY(aux.getPositionY() - 0.1f);
            }
        }
    }
}