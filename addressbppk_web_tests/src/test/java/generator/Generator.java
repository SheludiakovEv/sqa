package generator;
import com.beust.jcommander.Parameter;

public class Generator {

    @Parameter(names = {"--length", "-l"}, description = "Длина линии")
    private int length;

    public static void main(String[] args) {
        new Generator().run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        return null;
    }

    private void save(Object data) {
    }
}