package auxiliaryElements;

public class AuxComponent {
    private boolean flag;
    private float positionY;
    
    public AuxComponent(boolean flag, int positionY){
        this.flag = flag;
        this.positionY = positionY;
    }
    
    public AuxComponent(){
        flag = true;
        positionY = 5;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
}
