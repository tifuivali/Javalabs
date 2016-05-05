package AudioLibraryShell.Comand;

import AudioLibraryShell.ComandArgumentException;
import Models.FavoriteList;
import Models.TableFileModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class InfoComand
        implements Comand {

    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;

    public InfoComand(Path curentDir, List<File> recentlyListedFiels, String[] args) {
        this.path_cutentDirectory = curentDir;
        this.args = args;
        this.curent_listed_files = recentlyListedFiels;
    }

    public static void getGoogleInfo(File file) throws URISyntaxException, IOException {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("http://www.google.ro/#q=");
        String filenoext = FilenameUtils.getBaseName(file.getName());
        String[] words = filenoext.split(" ");
        strBuild.append(words[0]);

        for (int i = 1; i < words.length; i++) {
            strBuild.append("+").append(words[i]);
        }

        String uri = strBuild.toString().trim();

        Desktop desktop = Desktop.getDesktop();

        desktop.browse(new URI(uri));
    }

    private void parseInfo_comand(String... args)
            throws ComandArgumentException, IOException {
        if (args.length < 2) {
            throw new ComandArgumentException("Number of arguments too late.");
        }
        if ((args[1].equals("-i")) || (args[1].equals("-index"))) {
            int index = 0;
            String info = "";
            try {
                index = Integer.parseInt(args[2]);
                WriteMetadataInformation((File) this.curent_listed_files.get(index - 1));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new ComandArgumentException("Not valid index!");
            } catch (SAXException | TikaException ex) {
                System.out.println("Error getting info!");
            }
            return;
        }
        if ((args[1].equals("-f")) || (args[1].equals("-file"))) {
            StringBuilder strBuild = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                strBuild.append(args[i]).append(" ");
            }
            String str_path = strBuild.toString();
            if (Paths.get(str_path, new String[0]).getParent() == null) {
                File file1 = new File(this.path_cutentDirectory.toString());
                File filePlay = new File(file1, str_path.trim());
                try {
                    WriteMetadataInformation(filePlay);
                } catch (SAXException | TikaException ex) {
                    System.out.println("Error getting info!");
                }
            } else {
                Path path_new_directory = Paths.get(str_path, new String[0]);
                if (Files.exists(path_new_directory, new LinkOption[0])) {
                    try {
                        WriteMetadataInformation(path_new_directory.toFile());
                    } catch (SAXException | TikaException ex) {
                        System.out.println("Error getting info!");
                    }
                } else {
                    System.out.println("File not found!");
                }
            }
            return;
        }
        throw new ComandArgumentException("Arguments error!");
    }

    public static String GetInfoString(File file)
            throws FileNotFoundException, IOException, SAXException, TikaException {
        StringBuilder strBuilder = new StringBuilder();
        if (file.isDirectory()) {
            throw new FileNotFoundException("Directory!");
        }
        if (isAudioFile(file)) {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            parser.parse(inputstream, handler, metadata, context);

            String[] metadataNames = metadata.names();
            for (String name : metadataNames) {
                strBuilder.append(name).append(": ").append(metadata.get(name)).append("\n");
            }
        }
        return strBuilder.toString();
    }

    public static TableFileModel GetInfoTableModel(File directory)
            throws FileNotFoundException, IOException, SAXException, TikaException {
        if (!directory.isDirectory()) {
            throw new FileNotFoundException("NotDirectory!");
        }
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        String[] collums = {"File Name", "Title", "Artist", "Composer", "Genre", "Album", "Destinatie"};
        List<String[]> rows = new ArrayList();
        TableFileModel model = new TableFileModel(collums, rows);
        for (File file : directory.listFiles()) {
            if ((file.isFile()) && (isAudioFile(file))) {
                FileInputStream inputstream = new FileInputStream(file);
                ParseContext context = new ParseContext();

                parser.parse(inputstream, handler, metadata, context);
                String[] row = new String[7];
                row[0] = file.getName();
                row[1] = metadata.get("title");
                row[2] = metadata.get("xmpDM:artist");
                row[3] = metadata.get("xmpDM:composer");
                row[4] = metadata.get("xmpDM:genre");
                row[5] = metadata.get("xmpDM:album");
                row[6] = file.getAbsolutePath();
                model.addRow(row);
            }
        }
        return model;
    }

    public static TableFileModel GetInfoTableModel(FavoriteList favList)
            throws FileNotFoundException, IOException, SAXException, TikaException {

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        String[] collums = {"File Name", "Title", "Artist", "Composer", "Genre", "Album", "Destinatie"};
        List<String[]> rows = new ArrayList();
        TableFileModel model = new TableFileModel(collums, rows);
        for (File file : favList.getFiles()) {

            FileInputStream inputstream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            parser.parse(inputstream, handler, metadata, context);
            String[] row = new String[7];
            row[0] = file.getName();
            row[1] = metadata.get("title");
            row[2] = metadata.get("xmpDM:artist");
            row[3] = metadata.get("xmpDM:composer");
            row[4] = metadata.get("xmpDM:genre");
            row[5] = metadata.get("xmpDM:album");
            row[6] = file.getAbsolutePath();
            model.addRow(row);

        }
        return model;
    }

    public static boolean isAudioFile(File file) {
        String extension = FilenameUtils.getExtension(file.getName());

        return (extension.equals("mp3")) || (extension.equals("wav")) || (extension.equals("flac")) || (extension.equals("ac3"));
    }

    private void WriteMetadataInformation(File file)
            throws FileNotFoundException, IOException, SAXException, TikaException {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputstream, handler, metadata, context);

        String[] metadataNames = metadata.names();
        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }

    @Override
    public void execute()
            throws IOException, IllegalArgumentException, ComandArgumentException {
        parseInfo_comand(this.args);
    }
}
