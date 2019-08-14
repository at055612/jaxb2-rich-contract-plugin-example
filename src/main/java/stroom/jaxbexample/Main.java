package stroom.jaxbexample;

import com.sun.tools.xjc.Driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {


    private static final Path PROJECT_ROOT = Paths.get(".").toAbsolutePath().normalize();
    private static final Path SOURCE_ROOT = PROJECT_ROOT.resolve("src/main/java");
    private static final Path GEN_PKG_PATH = SOURCE_ROOT.resolve("stroom/jaxbexample/gen");

    public static void main(String[] args) throws Exception {

        final String[] xjcOptions = new String[]{
                "-xmlschema",
                "-extension",
                "-p", "stroom.jaxbexample.gen",
                "-d", SOURCE_ROOT.toString(),
                "-b", PROJECT_ROOT.resolve("bindings.xjb").toString(),
                "-quiet",
                PROJECT_ROOT.resolve("schema.xsd").toString(), //the source schema to gen classes from
                "-Xfluent-builder",
                "-Xinheritance",
//                "-Xsimplify",
        };

        if (Files.exists(GEN_PKG_PATH)) {
            Files.newDirectoryStream(GEN_PKG_PATH)
                    .forEach(path -> {
                        try {
                            System.out.println("Deleting file " + path.toString());
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            System.out.println("Deleting dir " + GEN_PKG_PATH.toString());
            Files.delete(GEN_PKG_PATH);
        }

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
