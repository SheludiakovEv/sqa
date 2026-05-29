package generator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctions;
import model.GroupDate;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if("groups".equals(type)){
            return generatGroups();
        } else if ("contacts".equals(type)) {
            return generatContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generatGroups() {
        var result = new ArrayList<GroupDate>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupDate()
                    .withName(CommonFunctions.randomString(i))
                    .withHeader(CommonFunctions.randomString(i))
                    .withFooter(CommonFunctions.randomString(i)));
        }
        return result;
    }

    private Object generatContacts() {
        return null;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат " + format);
        }
    }
}