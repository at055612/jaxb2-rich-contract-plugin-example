package stroom.jaxbexample;

import com.sun.tools.xjc.Driver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {


    private static final Path PROJECT_ROOT = Paths.get(".");
    private static final Path GEN_PATH = PROJECT_ROOT.resolve("src/main/java/stroom/jaxbexample/gen");

    public static void main(String[] args) throws Exception {

        final String[] xjcOptions = new String[]{
                "-xmlschema",
                "-extension",
                "-p", "stroom.jaxbexample.gen",
                "-d", GEN_PATH.toAbsolutePath().toString(),
                "-b", PROJECT_ROOT.resolve("bindings.xjb").toAbsolutePath().toString(),
                "-quiet",
                PROJECT_ROOT.resolve("schema.xsd").toAbsolutePath().toString(), //the source schema to gen classes from
                "-Xfluent-builder",
                "-Xinheritance",
        };

        System.out.println("Running XJC with arguments:");
        Arrays.stream(xjcOptions)
                .map(str -> "  " + str)
                .forEach(System.out::println);

        final int exitStatus = Driver.run(xjcOptions, System.out, System.out);

        if (exitStatus != 0) {
            System.out.print("Executing xjc failed");
            System.exit(1);
        }
    }
}
