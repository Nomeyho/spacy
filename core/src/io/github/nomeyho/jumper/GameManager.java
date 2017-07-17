package io.github.nomeyho.jumper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.nomeyho.jumper.UI.FpsCounter;
import io.github.nomeyho.jumper.levels.AbstractLevel;
import io.github.nomeyho.jumper.levels.UsualLevel;
import io.github.nomeyho.jumper.objects.AbstractGameObject;
import io.github.nomeyho.jumper.objects.Player;

public class GameManager {
    public Player player;
    public AbstractLevel level;

    public GameManager() {
        this.player = new Player(0, 0, 0);
        this.level = new UsualLevel();
    }

    public void update (float delta) {
        this.player.update(delta);
        for(AbstractGameObject go: this.level.objects)
            go.update(delta);
    }

    public void draw (SpriteBatch batch) {
        // Draw layer per layer
        for(int layer = Application.MIN_LAYER; layer < Application.MAX_LAYER; ++layer) {
            // Draw player
            if (this.player.location.getLayer() == layer)
                this.player.draw(batch);
            // Draw objects
            for(AbstractGameObject go: this.level.objects) {
                if(go.location.getLayer() == layer)
                    go.draw(batch);
            }
        }
        FpsCounter fpscounter = new FpsCounter(batch);
        fpscounter.draw();
    }

    /*

        private Player player;
    private LinkedList<Bell> bells = new LinkedList<Bell>();
    private int currentBellHeight = 0;
    private static final int MIN_BELL_HEIGHT = (int) Math.ceil(Player.HEIGHT);
    private static final float MIN_BELL_SPACE = 3;

    // Random
    private Random rand = new Random();
    private static final float MIN = 1;
    private static final float MAX = Viewport.DIM_X - 2;

    public GameManager () {
        this.player = new Player(Viewport.DIM_X / 2, 0);
    }

    public LinkedList<Bell> getBells () {
        return this.bells;
    }

    public Player getPlayer () {
        return this.player;
    }

    private float randomFloat (float min, float max) {
        return this.rand.nextFloat() * max + min;
    }

    private int randomInt(int min, int max) {
        return this.rand.nextInt(max) + min;
    }

    //
    1. find random X
    2. go lefft/right randomly by 3 at most
    //
    public void update () {
        // How high is the player?
        int playerY = (int) Math.floor(this.player.getLocation().y);

        // Generate bells for the next tiles
        int startY = this.currentBellHeight > MIN_BELL_HEIGHT ? this.currentBellHeight : MIN_BELL_HEIGHT;
        int maxY = playerY + Viewport.DIM_Y;
        for(int i=startY; i<maxY; ++i) {
            // Foreach Y, how many bells to spawn?
            int nbBells = this.randomInt(0, 2);
            float[] X = new float[nbBells];

            // Make sure they don't overlap
            int count = 0;
            while(count < nbBells) {
                float x = this.randomFloat(MIN, MAX);
                boolean overlap = false;
                // Check for overlapping
                for(int j=0; j<count; ++j) {
                    if(Math.abs(x - X[j]) < MIN_BELL_SPACE)
                        overlap = true;
                }

                if(!overlap) {
                    X[count] = x;
                    ++count;
                }
            }

            // Spawn bells
            for(int j=0; j<nbBells; ++j)
                this.bells.add(new Bell(X[j], i));
        }

        this.currentBellHeight = playerY + Viewport.DIM_Y;

        // Remove the bells for the low levels
        Iterator<Bell> it = this.bells.iterator();
        while(it.hasNext()) {
            Bell bell = it.next();
            if(bell.getLocation().y < playerY)
                it.remove();
        }
    }

     */
}