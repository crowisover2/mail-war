package com.example.sheilalasahido.true_project;

public class ButtonShoot extends Sprite{

    public ButtonShoot(TampilanGame view, GameActivity game){
        super(view, game);
        this.bitmap = Atur_Gambar.getScaledBitmapAlpha8(game, R.drawable.shootbutton96);
        this.lebar = this.bitmap.getWidth();
        this.tinggi = this.bitmap.getHeight();
    }

    @Override
    public void move(){
        this.x = this.Tampilan.getWidth() - this.lebar - 100;
        this.y = this.Tampilan.getHeight() - this.tinggi - 100;
    }
}
