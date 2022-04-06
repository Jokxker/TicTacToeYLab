import com.aleks.model.Player;
import com.aleks.services.Game;

import java.util.Arrays;

public class TestGame {
    public static void main(String[] args) {
        String step = "5";
        Player player = new Player("aleks", '0', "1");
        char[] field = new char[]{'X', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i'};
        String[] res = new Game().changeField(step, player, field);
        System.out.println(Arrays.toString(res));
    }
}
