import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class FileManager {

    private final static String OUTPUT_FILES="src/data/output/";

    private final static String INT_FILES="src/data/input/integer/";

    private final static String STRING_FILES="src/data/input/string/";

    private final boolean isInteger;

    private final boolean isAsc;

    private String fileDirectoryPath;

    private ArrayList<String> commandLineFileArgs;
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private ArrayList<String> arrayListString = new ArrayList<>();

    public FileManager(ArrayList<String> commandLineFileArgs,boolean isInteger,boolean isAsc){
        this.commandLineFileArgs=commandLineFileArgs;
        this.isInteger=isInteger;
        this.isAsc=isAsc;
        setFilesPath();
    }

    private void setFilesPath(){
        if (isInteger){
            fileDirectoryPath=INT_FILES;
        }
        else {
            fileDirectoryPath=STRING_FILES;
        }
    }


    public void fillArrayFromFile() throws IOException {
        createOutputFile();
        readIfInputFilesExists();
    }

    private void createOutputFile(){
        try {
            Files.deleteIfExists(Path.of(OUTPUT_FILES+commandLineFileArgs.get(0)));
            Files.createFile(Path.of(OUTPUT_FILES+commandLineFileArgs.get(0)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readIfInputFilesExists() throws IOException {
        File file = new File(fileDirectoryPath);
        String[] fileList = file.list();
            for (int i =1;i<commandLineFileArgs.size();i++){
                boolean isExists=false;
                for (String f: fileList){
                    if (f.equals(commandLineFileArgs.get(i))){
                        readFile(fileDirectoryPath+f);
                        isExists=true;
                    }

                }
                try {
                if(isExists==false)
                    throw new FileNotFoundException("Файла "+fileDirectoryPath+commandLineFileArgs.get(i) + " не существует");
                } catch (IOException e){
                    System.err.format("IOException: %s%n",e);
                }
            }
    }

    public void writeOutputFile(ArrayList<Integer> array){
        createOutputFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILES+commandLineFileArgs.get(0)))) {
            for (int i=0;i<array.size();i++) {
                if(isAsc)
                    bw.write(array.get(i) + "\n");
                else
                    bw.write(array.get(array.size()-1-i) + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOutputFileStr(ArrayList<String> array){
        createOutputFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILES+commandLineFileArgs.get(0)))) {
            for (int i=0;i<array.size();i++) {
                if(isAsc)
                    bw.write(array.get(i) + "\n");
                else
                    bw.write(array.get(array.size()-1-i) + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String path) throws IOException {
        boolean isSorted=true;
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            String line;
            int index=0;
            if(isInteger){
                while ((line = br.readLine()) != null) {
                    if(index>0){
                        index=0+arrayList.size();
                        if(Integer.parseInt(line)<arrayList.get(index-1)){
                            isSorted=false;
                        }
                    }
                    arrayList.add(Integer.parseInt(line));
                    index++;
                }

            }
            else {
                while ((line = br.readLine()) != null){
                    if(index>0){
                        index=0+arrayListString.size();
                        if(line.compareTo(arrayListString.get(index-1))<0){
                            isSorted=false;
                        }
                        if (line.contains(" "))
                            System.out.println("Файл "+path+ " содержит \" \" ");
                    }
                    arrayListString.add(line);
                    index++;
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        catch (NumberFormatException e) {
            System.err.format("NumberFormatException: Файл содержит недопустимые значения");
        }
        finally {
            arrayList.stream().forEach(s -> System.out.println(s));
                if(isSorted==false) System.out.println("Входной файл не отсортирован " + path);
            //throw new IOException("Файл " +path+ " не отсортирован \nФайл считан до момента появления ошибочных элементов");
        }
    }

    public ArrayList<Integer> getArrayList() {
        if(isInteger) return arrayList;
        else return null;
    }

    public ArrayList<String> getArrayListString() {
        if(isInteger) return null;
        else return arrayListString;
    }
}