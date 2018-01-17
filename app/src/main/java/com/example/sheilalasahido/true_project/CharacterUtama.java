package com.example.sheilalasahido.true_project;

public abstract class CharacterUtama extends Sprite{
    protected boolean isDead = false;

    public CharacterUtama(TampilanGame view, GameActivity game){
        super(view, game);
        move();
    }

    @Override
    public void move() {
        this.x = this.Tampilan.getWidth()/6;
        //this.y = this.Tampilan.getHeight()/2;

        if(speedY<0){
            speedY = speedY * 2/3 + getSpeedTimeDecrease() / 2;
        }else{
            // the character is moving down
            this.speedY += getSpeedTimeDecrease();
        }

        if(this.speedY > getMaxSpeed()){
            // speed limit
            this.speedY = getMaxSpeed();
        }

        super.move();

    }

    public boolean isDead(){
        return this.isDead;
    }

    public void dead(){
        this.isDead = true;
        this.speedY = getMaxSpeed()/2;
    }

    public void onTap(){
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
    }

    protected float getTabSpeed(){
        // -80 @ 720x1280 px
        return - Tampilan.getHeight() / 16f;
    }

    protected int getPosTabIncrease(){
        // -12 @ 720x1280 px
        return - Tampilan.getHeight() / 100;
    }

    protected float getSpeedTimeDecrease(){
        // 4 @ 720x1280 px
        return Tampilan.getHeight() / 320;
    }

    protected float getMaxSpeed(){
        // 25 @ 720x1280 px
        return Tampilan.getHeight() / 51.2f;
    }
}
