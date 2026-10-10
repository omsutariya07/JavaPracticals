import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FolderReport {

    public static void main(String[] args) {

        Path folder = Paths.get("myfolder");
        Path report = Paths.get("folder_report.txt");

        try {

            try (Stream<Path> paths = Files.walk(folder)) {

                StringBuilder result = new StringBuilder();

                result.append("FOLDER REPORT\n");
                result.append("====================\n\n");

                paths.filter(Files::isRegularFile)
                     .forEach(path -> {

                    try {

                        BasicFileAttributes attributes =
                            Files.readAttributes(
                                path,
                                BasicFileAttributes.class
                            );

                        result.append(
                            "File Name: "
                            + path.getFileName()
                            + "\n"
                        );

                        result.append(
                            "Path: "
                            + path
                            + "\n"
                        );

                        result.append(
                            "Size: "
                            + attributes.size()
                            + " bytes\n"
                        );

                        result.append(
                            "Modified: "
                            + attributes.lastModifiedTime()
                            + "\n"
                        );

                        result.append(
                            "--------------------\n"
                        );

                    } catch (IOException e) {
                        System.out.println(
                            "Could not read: " + path
                        );
                    }
                });

                Files.writeString(report, result.toString());
            }

            System.out.println(
                "Folder report created successfully."
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}