package board.level;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelUnitTest {

    private transient Level basicLevel;

    @BeforeEach
    public void setUp() {
        basicLevel = LevelParser.parseEmpty();
    }

    @Test
    public void getSpriteTest() {
        BufferedImage bi = basicLevel.getEdible().getSprite();
        assertThat(bi.getClass()).isEqualTo(BufferedImage.class);
    }

    @Test
    public void edibleColorTest() {
        Color col = basicLevel.getEdible().edibleColor();
        assertThat(col.getClass()).isEqualTo(Color.class);
    }

    @Test
    public void gameStatusTest() {
        assertThat(basicLevel.getGameStatus()).isEqualTo(LevelStatus.INITIALISED);
        basicLevel.setGameStatus(LevelStatus.PAUSED);
        basicLevel.initialise();
        assertThat(basicLevel.getGameStatus()).isEqualTo(LevelStatus.PAUSED);
    }

    @Test
    public void setInProgressTest() {
        basicLevel.setInProgress(false);
        assertThat(basicLevel.isInProgress()).isFalse();
    }
}
