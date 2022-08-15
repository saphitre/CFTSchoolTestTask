import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CommandLineParser {
    private static Boolean isInteger;

    private static boolean ascendingSort=true;


    public static boolean  isInteger(){
        return isInteger;
    }
    public static boolean isAscendingSort(){
        return ascendingSort;
    }

    /**
     * @TODO Добавить методы чтобы исключить двойной выбор файла
     * @param args
     * @return
     */
    public static ArrayList<String> parseCommandLineArgs(String[] args){
        if (args.length < 3) //т.к 3 обязательных параметра
            throw new RuntimeException("Недостаточно параметров командной строки. \nПример заполнения параметров командной строки: -i (-a/-d) out.txt in1.txt in2.txt");
            ArrayList<String> fileList = new ArrayList<>();
            if(args[0].equals("-i")|args[1].equals("-i")) isInteger=true;
            else if(args[0].equals("-s") | args[1].equals("-s")) isInteger=false;
            ascendingSort = (args[0].equals("-d") | args[1].equals("-d")) ? false : true;
            fileList = Arrays.stream(args).filter(s -> s.contains(".txt")).collect(Collectors.toCollection(ArrayList<String>::new));
            if (fileList.size() < 2)
                throw new RuntimeException("Недостаточно параметров-файлов комендной строки в формате .txt");
            if (isInteger == null)
                throw new RuntimeException("Не выбран тип файла, добавьте -s или -i при запуске");
            if (fileList.get(0).subSequence(0, 3).equals("out")==false)
                throw new RuntimeException("Выходной файл должен быть в формате out*.txt \nПример заполнения параметров командной строки: -i (-a/-d) out.txt in1.txt in2.txt");
            return fileList;

    }
}
