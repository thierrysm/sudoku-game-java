import ui.custom.screen.MainScreen;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
        Map<String, String> gameConfig;

        if (args.length == 0) {
            System.out.println("Nenhum argumento fornecido. Carregando tabuleiro padrão...");
            gameConfig = getDefaultConfig();
        } else {
            System.out.println("Carregando tabuleiro a partir de argumentos...");
            gameConfig = Stream.of(args)
                    .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
        }

        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }

    private static Map<String, String> getDefaultConfig() {
        Map<String, String> config = new HashMap<>();

        // --- Linha 0 ---
        config.put("0,0", "5,true");
        config.put("0,1", "3,true");
        config.put("0,2", "4,false");
        config.put("0,3", "6,false");
        config.put("0,4", "7,true");
        config.put("0,5", "8,false");
        config.put("0,6", "9,false");
        config.put("0,7", "1,false");
        config.put("0,8", "2,false");

        // --- Linha 1 ---
        config.put("1,0", "6,true");
        config.put("1,1", "7,false");
        config.put("1,2", "2,false");
        config.put("1,3", "1,true");
        config.put("1,4", "9,true");
        config.put("1,5", "5,true");
        config.put("1,6", "3,false");
        config.put("1,7", "4,false");
        config.put("1,8", "8,false");

        // --- Linha 2 ---
        config.put("2,0", "1,false");
        config.put("2,1", "9,true");
        config.put("2,2", "8,true");
        config.put("2,3", "3,false");
        config.put("2,4", "4,false");
        config.put("2,5", "2,false");
        config.put("2,6", "5,false");
        config.put("2,7", "6,true");
        config.put("2,8", "7,false");

        // --- Linha 3 ---
        config.put("3,0", "8,true");
        config.put("3,1", "5,false");
        config.put("3,2", "9,false");
        config.put("3,3", "7,false");
        config.put("3,4", "6,true");
        config.put("3,5", "1,false");
        config.put("3,6", "4,false");
        config.put("3,7", "2,false");
        config.put("3,8", "3,true");

        // --- Linha 4 ---
        config.put("4,0", "4,true");
        config.put("4,1", "2,false");
        config.put("4,2", "6,false");
        config.put("4,3", "8,true");
        config.put("4,4", "5,false");
        config.put("4,5", "3,true");
        config.put("4,6", "7,false");
        config.put("4,7", "9,false");
        config.put("4,8", "1,true");

        // --- Linha 5 ---
        config.put("5,0", "7,true");
        config.put("5,1", "1,false");
        config.put("5,2", "3,false");
        config.put("5,3", "9,false");
        config.put("5,4", "2,true");
        config.put("5,5", "4,false");
        config.put("5,6", "8,false");
        config.put("5,7", "5,false");
        config.put("5,8", "6,true");

        // --- Linha 6 ---
        config.put("6,0", "9,false");
        config.put("6,1", "6,true");
        config.put("6,2", "1,false");
        config.put("6,3", "5,false");
        config.put("6,4", "3,false");
        config.put("6,5", "7,false");
        config.put("6,6", "2,true");
        config.put("6,7", "8,true");
        config.put("6,8", "4,false");

        // --- Linha 7 ---
        config.put("7,0", "2,false");
        config.put("7,1", "8,false");
        config.put("7,2", "7,false");
        config.put("7,3", "4,true");
        config.put("7,4", "1,true");
        config.put("7,5", "9,true");
        config.put("7,6", "6,false");
        config.put("7,7", "3,false");
        config.put("7,8", "5,true");

        // --- Linha 8 ---
        config.put("8,0", "3,false");
        config.put("8,1", "4,false");
        config.put("8,2", "5,false");
        config.put("8,3", "2,false");
        config.put("8,4", "8,true");
        config.put("8,5", "6,false");
        config.put("8,6", "1,false");
        config.put("8,7", "7,true");
        config.put("8,8", "9,true");

        return config;
    }
}