package AmazingJava;

import java.io.*;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava
 * @description
 * @date 2018/10/29 13:48
 * <p>
 * 给出一个路径
 * 遍历路径下的文件
 * 可以通过单行或者全文检索需要的内容
 */
public class DirFileSearch {

    private static final String ROOT_DIR = "E:\\His\\JavaHisCode\\His\\java_his\\src\\main\\java\\com\\eyedsion\\his\\web\\service";

    //1读一行，2读一个文件
    private static final int statusType = 2;

    //单行检索
    private static boolean lineFilter(String data) {
        if (data.contains("function"))
            return true;
        return false;
    }

    //全文检索,检索目录下代码循环嵌套层数
    private static void filter(File file, String data) {
        String[] lineData = data.split("\n");
        int forIndex = 0;
        int index = 0;
        int forMax = 0;
        boolean[] booleans = new boolean[10000];
        try {
            //for{
            //}
            for (String lineDatum : lineData) {
                if (lineDatum.contains("{")) {
                    index++;
                    if (lineDatum.contains("for") || lineDatum.contains("while")) {
                        booleans[index] = true;
                        forIndex++;
                        forMax = Math.max(forIndex, forMax);
                    } else {
                        booleans[index] = false;
                    }

                }

                if (lineDatum.contains("}")) {
                    if (booleans[index] == true) {
                        forIndex--;
                    }

                    index--;
                }
            }
        } catch (Exception e) {
            System.out.println("error" + file.getAbsolutePath());
        }

        if (forMax >= 3) {
            System.out.println(file.getAbsolutePath() + "-- " + forMax);
        }
    }

    public static void main(String[] args) {
        File file = new File(ROOT_DIR);
        deepFile(file);
    }

    private static void deepFile(File inFile) {
        BufferedReader reader = null;
        if (inFile.exists() && inFile.isDirectory()) {
            File[] tempList = inFile.listFiles();
            for (File file : tempList) {
                if (file.isDirectory()) {
                    deepFile(file);
                } else {

                    try {
                        reader = new BufferedReader(new FileReader(file));
                        String data = null;
                        int lineIndex = 1;
                        if (statusType == 1) {
                            while ((data = reader.readLine()) != null) {
                                if (lineFilter(data)) {
                                    System.out.println(String.format("[%40s] in [%d] line find data [%s]", file.getAbsoluteFile(), lineIndex, data));
                                }
                                lineIndex++;
                            }
                        } else {
                            String tempData;
                            while ((tempData = reader.readLine()) != null) {
                                data += tempData + "\n";
                            }
                            filter(file, data);
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        try {
            if (reader != null)
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
