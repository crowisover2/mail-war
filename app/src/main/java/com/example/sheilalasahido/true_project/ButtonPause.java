package com.example.sheilalasahido.true_project;

public class ButtonPause extends Sprite{
    public ButtonPause(TampilanGame view, GameActivity game) {
        super(view, game);
        this.bitmap = Atur_Gambar.getScaledBitmapAlpha8(game, R.drawable.pausebutton96);
        this.lebar = this.bitmap.getWidth();
        this.tinggi = this.bitmap.getHeight();
    }

    @Override
    public void move(){
        this.x = this.Tampilan.getWidth() - this.lebar;
        this.y = 0;
    }
}
