package edu.zju.cst.AnomalyDetection.utils;

import org.apache.commons.fileupload.util.Streams;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PythonRunner {

    public static void pythonExec(String[] args){
        try {
            //args = new String[] { "python", "/api_test.py", a, b, c, d };
            //String[] args = new String[] { "/home/hadoop/anaconda3/bin/python", "/home/hadoop/PycharmProjects/securitai-lstm-model/tt.py"};
            Process pr = Runtime.getRuntime().exec(args);
            try (FileOutputStream out = new FileOutputStream("/home/hadoop/pylog.log")) {
                Streams.copy(pr.getErrorStream(), out,false);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
