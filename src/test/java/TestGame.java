import com.aleks.model.Player;
import com.aleks.services.Game;
import com.aleks.utils.AdapterField;
import static org.junit.jupiter.api.Assertions.*;
public class TestGame {
    public static void main(String[] args) {
        String step = "5";
        Player player = new Player("aleks", '0', "1");
        char[] field = new char[]{'X', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i'};
        String expected = "Xiii0iiii";
        String[] res = Game.changeField(step, player, AdapterField.adapterField(field));
        assertEquals(expected, res[0]);
    }
}
