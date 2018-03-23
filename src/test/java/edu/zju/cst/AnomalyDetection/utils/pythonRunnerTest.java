package edu.zju.cst.AnomalyDetection.utils;

import org.junit.Test;

public class pythonRunnerTest {

    @Test
    public void pythonExecTest(){
        String[] args = new String[] { "/home/hadoop/anaconda3/bin/python", "/home/hadoop/PycharmProjects/securitai-lstm-model/tt.py"};
        PythonRunner.pythonExec(args);

    }
}
