package com.kaiju.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Skronak on 09/02/2017.
 */
public class AnimatedActor extends Actor {

        private Animation idleAnimation;
        private Animation deathAnimation;
        private float deltatime;
        private TextureRegion currentFrame;
        private Animation currentAnimation;

        public AnimatedActor(int posX, int posY, int width, int height, float animSpeed, Array<TextureRegion> frames, Array<TextureRegion> framesDeath, Animation.PlayMode playMode) {
            deltatime = 0;
            this.setWidth(width);
            this.setHeight(height);
            this.setPosition(posX, posY);
            if (null != frames) {
                idleAnimation = new Animation(animSpeed, frames);
                idleAnimation.setPlayMode(playMode);
                deathAnimation = new Animation(animSpeed, framesDeath);
                deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
            }
            currentAnimation = idleAnimation;
        }

        public void playDeath(){
            currentAnimation = deathAnimation;
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            currentFrame = (TextureRegion) currentAnimation.getKeyFrame(deltatime, true);
            batch.draw(currentFrame,getX(),getY(),getWidth(),getHeight());
        }

        @Override
        public void act(float deltaTime)
        {
            super.act(deltaTime);
            deltatime += deltaTime;
        }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setDeltatime(float deltatime) {
        this.deltatime = deltatime;
    }

    public Animation getIdleAnimation() {
        return idleAnimation;
    }

    public void setIdleAnimation(Animation idleAnimation) {
        this.idleAnimation = idleAnimation;
    }

    public Animation getDeathAnimation() {
        return deathAnimation;
    }

    public void setDeathAnimation(Animation deathAnimation) {
        this.deathAnimation = deathAnimation;
    }

    public float getDeltatime() {
        return deltatime;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
}
